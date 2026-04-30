package com.batterycare.app.ui

import android.content.Intent
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.batterycare.app.BatteryCareApplication
import com.batterycare.app.databinding.ActivityMainBinding
import com.batterycare.app.ui.viewmodel.BatteryViewModel
import com.batterycare.app.ui.viewmodel.BatteryViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: BatteryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel
        val factory = BatteryViewModelFactory(BatteryCareApplication.database.batteryDao())
        viewModel = ViewModelProvider(this, factory)[BatteryViewModel::class.java]

        // Setup UI listeners
        setupListeners()

        // Observe battery data
        observeBatteryData()

        // Register battery receiver
        registerBatteryReceiver()
    }

    private fun setupListeners() {
        binding.btnDetails.setOnClickListener {
            startActivity(Intent(this, BatteryDetailActivity::class.java))
        }

        binding.btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        binding.btnOptimize.setOnClickListener {
            viewModel.optimizeBattery()
        }
    }

    private fun observeBatteryData() {
        viewModel.batteryHealth.observe(this) { health ->
            binding.tvBatteryHealth.text = "Sức khỏe Pin: $health"
        }

        viewModel.batteryPercentage.observe(this) { percentage ->
            binding.tvBatteryPercentage.text = "Mức Pin: $percentage%"
            binding.pbBattery.progress = percentage
        }

        viewModel.batteryTemperature.observe(this) { temperature ->
            binding.tvBatteryTemperature.text = "Nhiệt độ: $temperature°C"
        }

        viewModel.batteryVoltage.observe(this) { voltage ->
            binding.tvBatteryVoltage.text = "Điện áp: $voltage mV"
        }

        viewModel.isCharging.observe(this) { charging ->
            val status = if (charging) "Đang sạc" else "Không sạc"
            binding.tvBatteryStatus.text = "Trạng thái sạc: $status"
        }
    }

    private fun registerBatteryReceiver() {
        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        val batteryStatus = registerReceiver(null, filter, Build.VERSION.SDK_INT)

        batteryStatus?.let {
            val level = it.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale = it.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            val percentage = (level * 100) / scale.toFloat()

            val temperature = it.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1)
            val voltage = it.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1)
            val health = it.getIntExtra(BatteryManager.EXTRA_HEALTH, -1)
            val status = it.getIntExtra(BatteryManager.EXTRA_STATUS, -1)

            val isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING
            val healthString = when (health) {
                BatteryManager.BATTERY_HEALTH_GOOD -> "Tốt"
                BatteryManager.BATTERY_HEALTH_COLD -> "Lạnh"
                BatteryManager.BATTERY_HEALTH_OVERHEAT -> "Quá nóng"
                BatteryManager.BATTERY_HEALTH_DEAD -> "Hỏng"
                else -> "Bình thường"
            }

            viewModel.updateBatteryData(
                percentage.toInt(),
                healthString,
                temperature,
                voltage,
                isCharging
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            unregisterReceiver(null)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
