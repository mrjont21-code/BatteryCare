package com.batterycare.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "battery_data")
data class BatteryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val timestamp: Long,
    val level: Int,
    val temperature: Int,
    val voltage: Int,
    val current: Int,
    val health: String,
    val status: String,
    val technology: String,
    val capacity: Int,
    val cycleCount: Int = 0
)

@Entity(tableName = "charging_sessions")
data class ChargingSessionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val startTime: Long,
    val endTime: Long = 0,
    val maxTemp: Int = 0,
    val avgCurrent: Int = 0,
    val finalHealth: String = "",
    val startLevel: Int = 0,
    val endLevel: Int = 0
)
