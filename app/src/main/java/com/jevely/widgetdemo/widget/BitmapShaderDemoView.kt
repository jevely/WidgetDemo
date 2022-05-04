package com.jevely.widgetdemo.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.jevely.widgetdemo.R

/**
 * 用BitmapShader做一个望远镜效果
 */
class BitmapShaderDemoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint()
    private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.testimg)
    private var bitmapWidth = 0
    private var bitmapHeight = 0
    private var drawRect = Rect()

    init {
        paint.isAntiAlias = true
        paint.isDither = true
        paint.shader = BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)

        bitmapWidth = bitmap.width
        bitmapHeight = bitmap.height
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawRect(drawRect, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {

            }
            MotionEvent.ACTION_MOVE -> {
                drawRect.left = (event.x - 100).toInt()
                drawRect.top = (event.y - 100).toInt()
                drawRect.right = (event.x + 100).toInt()
                drawRect.bottom = (event.y + 100).toInt()

                postInvalidate()
            }
        }
        return true
    }

}