package com.batterycare.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.batterycare.app.BatteryCareApplication
import com.batterycare.app.databinding.ActivityBatteryDetailBinding
import com.batterycare.app.ui.viewmodel.BatteryViewModel
import com.batterycare.app.ui.viewmodel.BatteryViewModelFactory

class BatteryDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBatteryDetailBinding
    private lateinit var viewModel: BatteryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBatteryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Initialize ViewModel
        val factory = BatteryViewModelFactory(BatteryCareApplication.database.batteryDao())
        viewModel = ViewModelProvider(this, factory)[BatteryViewModel::class.java]

        // Observe and display battery data
        observeBatteryData()
    }

    private fun observeBatteryData() {
        viewModel.batteryPercentage.observe(this) { percentage ->
            binding.tvDetailPercentage.text = "$percentage%"
        }

        viewModel.batteryHealth.observe(this) { health ->
            binding.tvDetailHealth.text = health
        }

        viewModel.batteryTemperature.observe(this) { temperature ->
            binding.tvDetailTemperature.text = "$temperature°C"
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
