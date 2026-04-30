package com.batterycare.app.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.batterycare.app.util.ChargeCurvePoint

class ChargingCurveView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var referenceCurve: List<ChargeCurvePoint> = emptyList()
    private var actualCurve: List<ChargeCurvePoint> = emptyList()
    private val referencePaint = Paint().apply {
        color = Color.parseColor("#4CAF50")
        strokeWidth = 3f
        isAntiAlias = true
        style = Paint.Style.STROKE
        pathEffect = android.graphics.DashPathEffect(floatArrayOf(10f, 5f), 0f)
    }
    private val actualPaint = Paint().apply {
        color = Color.parseColor("#2196F3")
        strokeWidth = 3f
        isAntiAlias = true
        style = Paint.Style.STROKE
    }
    private val axisPaint = Paint().apply {
        color = Color.WHITE
        strokeWidth = 1f
        isAntiAlias = true
    }
    private val textPaint = Paint().apply {
        color = Color.WHITE
        textSize = 24f
        isAntiAlias = true
    }

    fun setData(reference: List<ChargeCurvePoint>, actual: List<ChargeCurvePoint>) {
        referenceCurve = reference.take(100)
        actualCurve = actual.take(100)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val padding = 50f
        val width = width - 2 * padding
        val height = height - 2 * padding

        if (width > 0 && height > 0) {
            drawAxes(canvas, padding, width, height)
            if (referenceCurve.isNotEmpty()) drawCurve(canvas, referenceCurve, referencePaint, padding, width, height)
            if (actualCurve.isNotEmpty()) drawCurve(canvas, actualCurve, actualPaint, padding, width, height)
        }
    }

    private fun drawAxes(canvas: Canvas, padding: Float, width: Float, height: Float) {
        canvas.drawLine(padding, height + padding, padding + width, height + padding, axisPaint)
        canvas.drawLine(padding, padding, padding, height + padding, axisPaint)
    }

    private fun drawCurve(canvas: Canvas, curve: List<ChargeCurvePoint>, paint: Paint, padding: Float, width: Float, height: Float) {
        if (curve.size < 2) return
        val path = Path()
        val maxLevel = 100f
        val maxTime = curve.maxOfOrNull { it.expectedTime }?.toFloat() ?: 3600f

        curve.forEachIndexed { idx, point ->
            val x = padding + (point.level / maxLevel) * width
            val y = height + padding - (point.expectedTime / maxTime) * height
            if (idx == 0) path.moveTo(x, y) else path.lineTo(x, y)
        }
        canvas.drawPath(path, paint)
    }
}
