package com.batterycare.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.batterycare.app.data.BatteryDatabase
import com.batterycare.app.data.BatteryEntity
import com.batterycare.app.repository.BatteryRepository
import com.batterycare.app.util.BatteryInfo
import kotlinx.coroutines.launch

class BatteryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: BatteryRepository
    val latestBatteryData: LiveData<BatteryEntity?>
    private val _batteryInfo = MutableLiveData<BatteryInfo>()
    val batteryInfo: LiveData<BatteryInfo> = _batteryInfo

    init {
        val database = BatteryDatabase.getDatabase(application)
        repository = BatteryRepository(database.batteryDao())
        latestBatteryData = repository.getLatestBatteryData()
    }

    fun insertBatteryData(entity: BatteryEntity) {
        viewModelScope.launch {
            repository.insertBatteryData(entity)
        }
    }

    fun updateBatteryInfo(info: BatteryInfo) {
        _batteryInfo.postValue(info)
    }

    fun deleteOldData(olderThan: Long) {
        viewModelScope.launch {
            repository.deleteOldData(olderThan)
        }
    }
}
