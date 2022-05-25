package com.jevely.widgetdemo.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.jevely.widgetdemo.R

/**
 * 橡皮擦效果的图片 ｜ 刮刮卡
 */
class EraserImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private val bitmapPaint = Paint()
    private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.testimage)
    private val xFermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OUT)
    private val fingerPath = Path()

    var downPointX = 0f
    var downPointY = 0f

    private val textPaint = Paint()

    init {
        bitmapPaint.isAntiAlias = true
        bitmapPaint.isDither = true
        bitmapPaint.style = Paint.Style.STROKE
        bitmapPaint.strokeWidth = 30f
        bitmapPaint.color = Color.BLUE

        textPaint.isAntiAlias = true
        textPaint.textSize = 20f
        textPaint.color = Color.BLACK
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawText("你中奖了", 0f, 50f, textPaint)

        val a =
            canvas?.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null, Canvas.ALL_SAVE_FLAG)

        canvas?.drawBitmap(bitmap, 0f, 0f, bitmapPaint)
        bitmapPaint.xfermode = xFermode
        canvas?.drawPath(fingerPath, bitmapPaint)
        bitmapPaint.xfermode = null

        canvas?.restoreToCount(a!!)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                downPointX = event.x
                downPointY = event.y
                fingerPath.moveTo(downPointX, downPointY)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                val movePointX = event.x
                val movePointY = event.y
                val centerX = (movePointX + downPointX) / 2.0f
                val centerY = (movePointY + downPointY) / 2.0f
                fingerPath.quadTo(downPointX, downPointY, centerX, centerY)

                downPointX = movePointX
                downPointY = movePointY

                postInvalidate()

                return false
            }
        }
        return super.onTouchEvent(event)
    }

}