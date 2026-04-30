package com.batterycare.app.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface BatteryDao {
    @Insert
    suspend fun insertBatteryData(entity: BatteryEntity)

    @Insert
    suspend fun insertChargingSession(entity: ChargingSessionEntity)

    @Query("SELECT * FROM battery_data ORDER BY timestamp DESC LIMIT 1")
    fun getLatestBatteryData(): LiveData<BatteryEntity?>

    @Query("SELECT * FROM battery_data WHERE timestamp >= :startTime ORDER BY timestamp")
    suspend fun getBatteryDataSince(startTime: Long): List<BatteryEntity>

    @Query("SELECT * FROM charging_sessions ORDER BY startTime DESC")
    fun getChargingSessions(): LiveData<List<ChargingSessionEntity>>

    @Query("DELETE FROM battery_data WHERE timestamp < :olderThan")
    suspend fun deleteOldData(olderThan: Long)
}
