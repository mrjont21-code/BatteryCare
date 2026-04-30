package com.batterycare.app.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.util.Log

class BatteryReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BATTERY_CHANGED) {
            val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val temp = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0) / 10
            val voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0)
            val health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1)
            Log.d("BatteryReceiver", "Level: $level%, Temp: $temp°C, Voltage: ${voltage}mV, Health: $health")
        }
    }
}
