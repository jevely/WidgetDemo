package com.jevely.widgetdemo.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * 贝塞尔曲线实现钢笔轨迹
 */
class PenPaintView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val path = Path()
    private val paint = Paint()

    var downPointX = 0f
    var downPointY = 0f

    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 6f
        paint.color = Color.BLUE
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawColor(Color.WHITE)
        canvas?.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                downPointX = event.x
                downPointY = event.y
                path.moveTo(downPointX, downPointY)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                val movePointX = event.x
                val movePointY = event.y
                val centerX = (movePointX + downPointX) / 2.0f
                val centerY = (movePointY + downPointY) / 2.0f
                path.quadTo(downPointX, downPointY, centerX, centerY)

                downPointX = movePointX
                downPointY = movePointY

                postInvalidate()

                return false
            }
        }


        return super.onTouchEvent(event)
    }

}