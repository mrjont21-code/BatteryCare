package com.batterycare.app.util

data class CurveDeviation(
    val isAbnormal: Boolean,
    val reason: String,
    val deviation: Double
)

class ChargeCurveComparator {
    fun compare(
        actualCurve: List<ChargeCurvePoint>,
        referenceCurve: List<ChargeCurvePoint>
    ): CurveDeviation {
        if (actualCurve.isEmpty() || referenceCurve.isEmpty()) {
            return CurveDeviation(false, "Insufficient data", 0.0)
        }

        var totalDeviation = 0.0
        var count = 0

        for (actual in actualCurve) {
            val reference = referenceCurve.find { it.level == actual.level }
            if (reference != null) {
                val timeDiff = Math.abs(actual.expectedTime - reference.expectedTime)
                val deviation = (timeDiff.toDouble() / reference.expectedTime) * 100
                totalDeviation += deviation
                count++
            }
        }

        val avgDeviation = if (count > 0) totalDeviation / count else 0.0

        return CurveDeviation(
            isAbnormal = avgDeviation > 20.0,
            reason = if (avgDeviation > 20.0) "Slow charging detected" else "Normal",
            deviation = avgDeviation
        )
    }
}
