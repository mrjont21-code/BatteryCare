package com.batterycare.app.util

import android.content.Context
import org.json.JSONObject

data class ChargeCurvePoint(val level: Int, val expectedTime: Long)

class ReferenceChargeCurve(private val context: Context) {
    fun loadReferenceCurve(): List<ChargeCurvePoint> {
        return try {
            val json = context.assets.open("reference_curve.json")
                .bufferedReader().use { it.readText() }
            val obj = JSONObject(json)
            val keys = obj.keys()
            val points = mutableListOf<ChargeCurvePoint>()
            while (keys.hasNext()) {
                val level = keys.next().toInt()
                val time = obj.getLong(level.toString())
                points.add(ChargeCurvePoint(level, time))
            }
            points.sortedBy { it.level }
        } catch (e: Exception) {
            e.printStackTrace()
            defaultReferenceCurve()
        }
    }

    private fun defaultReferenceCurve(): List<ChargeCurvePoint> {
        return listOf(
            ChargeCurvePoint(0, 0),
            ChargeCurvePoint(20, 300),
            ChargeCurvePoint(40, 600),
            ChargeCurvePoint(60, 1200),
            ChargeCurvePoint(80, 2000),
            ChargeCurvePoint(100, 3600)
        )
    }
}
