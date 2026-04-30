package com.batterycare.app.service

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import androidx.core.app.ServiceCompat
import com.batterycare.app.data.BatteryDatabase
import com.batterycare.app.data.BatteryEntity
import com.batterycare.app.receiver.BatteryReceiver
import com.batterycare.app.util.BatteryUtil
import com.batterycare.app.util.CheckpointLogger
import com.batterycare.app.util.NotificationHelper
import com.batterycare.app.util.SoundPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BatteryMonitorService : Service() {
    private val scope = CoroutineScope(Dispatchers.IO + Job())
    private lateinit var notificationHelper: NotificationHelper
    private lateinit var batteryUtil: BatteryUtil
    private lateinit var soundPlayer: SoundPlayer
    private lateinit var checkpointLogger: CheckpointLogger
    private lateinit var db: BatteryDatabase
    private val handler = Handler(Looper.getMainLooper())
    private var lastLevel = -1
    private var isCharging = false

    override fun onCreate() {
        super.onCreate()
        notificationHelper = NotificationHelper(this)
        batteryUtil = BatteryUtil(this)
        soundPlayer = SoundPlayer(this)
        checkpointLogger = CheckpointLogger(this)
        db = BatteryDatabase.getDatabase(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startMonitoring()
        val notification = notificationHelper.showBatteryStatus(0, "Starting").build()
        ServiceCompat.startForeground(this, NotificationHelper.NOTIFICATION_ID, notification, 0)
        return START_STICKY
    }

    private fun startMonitoring() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                val info = batteryUtil.getBatteryInfo()
                scope.launch {
                    val entity = BatteryEntity(
                        timestamp = System.currentTimeMillis(),
                        level = info.level,
                        temperature = info.temperature,
                        voltage = info.voltage,
                        current = info.current,
                        health = info.health,
                        status = info.status,
                        technology = info.technology,
                        capacity = info.capacity
                    )
                    db.batteryDao().insertBatteryData(entity)
                    checkpointLogger.writeCheckpoint(info.level, info.status, info.health)
                }

                if (lastLevel != info.level) {
                    lastLevel = info.level
                    if (info.level == 100 && info.status.contains("Charging")) {
                        soundPlayer.playChargeCompleteSound()
                        notificationHelper.showAlert("Pin đầy 100%", "Pin của bạn đã sạc đầy")
                    }
                }

                if (info.temperature > 45) {
                    soundPlayer.playAlertSound()
                    notificationHelper.showAlert("Cảnh báo", "Nhiệt độ pin cao: ${info.temperature}°C")
                }

                handler.postDelayed(this, 5000)
            }
        }, 1000)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
        scope.cancel()
    }
}
