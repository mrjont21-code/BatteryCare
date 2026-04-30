package com.batterycare.app.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.batterycare.app.data.database.dao.BatteryDao
import com.batterycare.app.data.model.BatteryRecord
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BatteryViewModel(private val batteryDao: BatteryDao) : ViewModel() {

    private val _batteryPercentage = MutableLiveData<Int>()
    val batteryPercentage: LiveData<Int> get() = _batteryPercentage

    private val _batteryHealth = MutableLiveData<String>()
    val batteryHealth: LiveData<String> get() = _batteryHealth

    private val _batteryTemperature = MutableLiveData<Int>()
    val batteryTemperature: LiveData<Int> get() = _batteryTemperature

    private val _batteryVoltage = MutableLiveData<Int>()
    val batteryVoltage: LiveData<Int> get() = _batteryVoltage

    private val _isCharging = MutableLiveData<Boolean>()
    val isCharging: LiveData<Boolean> get() = _isCharging

    private val _batteryRecords = MutableLiveData<List<BatteryRecord>>()
    val batteryRecords: LiveData<List<BatteryRecord>> get() = _batteryRecords

    fun updateBatteryData(
        percentage: Int,
        health: String,
        temperature: Int,
        voltage: Int,
        charging: Boolean
    ) {
        _batteryPercentage.value = percentage
        _batteryHealth.value = health
        _batteryTemperature.value = temperature
        _batteryVoltage.value = voltage
        _isCharging.value = charging

        // Save to database
        viewModelScope.launch {
            val record = BatteryRecord(
                percentage = percentage,
                health = health,
                temperature = temperature,
                voltage = voltage,
                isCharging = charging,
                timestamp = System.currentTimeMillis()
            )
            batteryDao.insertRecord(record)
        }
    }

    fun optimizeBattery() {
        // Optimization logic would go here
        // For now, this is a placeholder
    }

    fun getBatteryRecords() {
        viewModelScope.launch {
            val records = batteryDao.getAllRecords()
            _batteryRecords.postValue(records)
        }
    }
}

class BatteryViewModelFactory(private val batteryDao: BatteryDao) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BatteryViewModel::class.java)) {
            return BatteryViewModel(batteryDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
