package com.batterycare.app.repository

import androidx.lifecycle.LiveData
import com.batterycare.app.data.BatteryDao
import com.batterycare.app.data.BatteryEntity
import com.batterycare.app.data.ChargingSessionEntity

class BatteryRepository(private val dao: BatteryDao) {
    suspend fun insertBatteryData(entity: BatteryEntity) {
        dao.insertBatteryData(entity)
    }

    suspend fun insertChargingSession(entity: ChargingSessionEntity) {
        dao.insertChargingSession(entity)
    }

    fun getLatestBatteryData(): LiveData<BatteryEntity?> {
        return dao.getLatestBatteryData()
    }

    suspend fun getBatteryDataSince(startTime: Long): List<BatteryEntity> {
        return dao.getBatteryDataSince(startTime)
    }

    fun getChargingSessions(): LiveData<List<ChargingSessionEntity>> {
        return dao.getChargingSessions()
    }

    suspend fun deleteOldData(olderThan: Long) {
        dao.deleteOldData(olderThan)
    }
}
