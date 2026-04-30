package com.batterycare.app.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.batterycare.app.data.model.BatteryRecord

@Dao
interface BatteryDao {

    @Insert
    suspend fun insertRecord(record: BatteryRecord)

    @Update
    suspend fun updateRecord(record: BatteryRecord)

    @Delete
    suspend fun deleteRecord(record: BatteryRecord)

    @Query("SELECT * FROM battery_records ORDER BY timestamp DESC LIMIT 1")
    suspend fun getLatestRecord(): BatteryRecord?

    @Query("SELECT * FROM battery_records ORDER BY timestamp DESC")
    suspend fun getAllRecords(): List<BatteryRecord>

    @Query("SELECT * FROM battery_records WHERE timestamp BETWEEN :startTime AND :endTime ORDER BY timestamp DESC")
    suspend fun getRecordsBetween(startTime: Long, endTime: Long): List<BatteryRecord>

    @Query("DELETE FROM battery_records WHERE timestamp < :threshold")
    suspend fun deleteOldRecords(threshold: Long)

    @Query("SELECT COUNT(*) FROM battery_records")
    suspend fun getRecordCount(): Int
}
