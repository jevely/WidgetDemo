package com.jevely.widgetdemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/**
 * 运用Path画了一个战力图
 */
class PowerValueView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val linePaint = Paint()
    private val pathPaint = Paint()

    private val centerX = 150f
    private val centerY = 150f

    private val powers = arrayOf(5, 4, 6, 6, 3, 6)

    init {

        linePaint.isAntiAlias = true
        linePaint.color = Color.BLUE
        linePaint.style = Paint.Style.STROKE
        linePaint.strokeWidth = 2f

        pathPaint.isAntiAlias = true
        pathPaint.color = Color.RED
        pathPaint.style = Paint.Style.FILL_AND_STROKE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.GRAY)
        drawLine(canvas)
        drawLine2(canvas)
        drawPower(canvas)
    }

    private fun drawLine(canvas: Canvas) {
        val path = Path()
        val r = 140 / 6
        for (i in 1..6) {
            val currentRadio = r * i
            path.reset()
            for (j in 0 until 6) {
                if (j == 0) {
                    path.moveTo(centerX + currentRadio, centerY)
                } else {
                    //cos sin 这里传入的是弧长
                    val drawX = centerX + currentRadio * Math.cos((60 * j * 2 * Math.PI / 360))
                    val drawY = centerY + currentRadio * Math.sin((60 * j * 2 * Math.PI / 360))
                    path.lineTo(drawX.toFloat(), drawY.toFloat())
                }
            }
            path.close()
            canvas.drawPath(path, linePaint)
        }
    }

    private fun drawLine2(canvas: Canvas) {
        val path = Path()
        val currentRadio = 140
        for (j in 0 until 6) {
            path.reset()
            path.moveTo(centerX, centerY)
            if (j == 0) {
                path.lineTo(centerX + currentRadio, centerY)
            } else {
                //cos sin 这里传入的是弧长
                val drawX = centerX + currentRadio * Math.cos((60 * j * 2 * Math.PI / 360))
                val drawY = centerY + currentRadio * Math.sin((60 * j * 2 * Math.PI / 360))
                path.lineTo(drawX.toFloat(), drawY.toFloat())
            }
            path.close()
            canvas.drawPath(path, linePaint)
        }
    }

    private fun drawPower(canvas: Canvas) {
        val path = Path()
        val r = 140 / 6
        powers.forEachIndexed { index, power ->
            val currentRadio = r * power
            val j = index
            if (j == 0) {
                path.moveTo(centerX + currentRadio, centerY)
                canvas.drawCircle(centerX + currentRadio, centerY, 4f, pathPaint)
            } else {
                //cos sin 这里传入的是弧长
                val drawX = centerX + currentRadio * Math.cos((60 * j * 2 * Math.PI / 360))
                val drawY = centerY + currentRadio * Math.sin((60 * j * 2 * Math.PI / 360))
                canvas.drawCircle(drawX.toFloat(), drawY.toFloat(), 4f, pathPaint)
                path.lineTo(drawX.toFloat(), drawY.toFloat())
            }
        }
        path.close()
        pathPaint.alpha = 150
        canvas.drawPath(path, pathPaint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(300, 300)
    }

}