package com.batterycare.app.util

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build

data class BatteryInfo(
    val level: Int = 0,
    val temperature: Int = 0,
    val voltage: Int = 0,
    val current: Int = 0,
    val health: String = "Unknown",
    val status: String = "Unknown",
    val technology: String = "Unknown",
    val capacity: Int = 100
)

class BatteryUtil(private val context: Context) {
    fun getBatteryInfo(): BatteryInfo {
        val batteryManager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        val batteryStatus = context.registerReceiver(null, intentFilter)

        return if (batteryStatus != null) {
            val level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val temperature = batteryStatus.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0) / 10
            val voltage = batteryStatus.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0)
            val current = try {
                batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW) / 1000
            } catch (e: Exception) {
                0
            }
            val healthCode = batteryStatus.getIntExtra(BatteryManager.EXTRA_HEALTH, -1)
            val statusCode = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
            val technology = batteryStatus.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY) ?: "Unknown"
            val capacity = try {
                batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_FULL) / 1000
            } catch (e: Exception) {
                100
            }

            BatteryInfo(
                level = level,
                temperature = temperature,
                voltage = voltage,
                current = current,
                health = healthCodeToString(healthCode),
                status = statusCodeToString(statusCode),
                technology = technology,
                capacity = capacity
            )
        } else {
            BatteryInfo()
        }
    }

    private fun healthCodeToString(code: Int): String = when (code) {
        BatteryManager.BATTERY_HEALTH_COLD -> "Cold"
        BatteryManager.BATTERY_HEALTH_COOL -> "Cool"
        BatteryManager.BATTERY_HEALTH_GOOD -> "Good"
        BatteryManager.BATTERY_HEALTH_OVERHEAT -> "Overheat"
        BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE -> "Failure"
        else -> "Unknown"
    }

    private fun statusCodeToString(code: Int): String = when (code) {
        BatteryManager.BATTERY_STATUS_CHARGING -> "Charging"
        BatteryManager.BATTERY_STATUS_DISCHARGING -> "Discharging"
        BatteryManager.BATTERY_STATUS_FULL -> "Full"
        BatteryManager.BATTERY_STATUS_NOT_CHARGING -> "NotCharging"
        else -> "Unknown"
    }
}
