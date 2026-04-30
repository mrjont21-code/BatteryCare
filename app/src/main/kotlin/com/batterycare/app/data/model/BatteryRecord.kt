package com.batterycare.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "battery_records")
data class BatteryRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val percentage: Int,
    val health: String,
    val temperature: Int,
    val voltage: Int,
    val isCharging: Boolean,
    val timestamp: Long
)
