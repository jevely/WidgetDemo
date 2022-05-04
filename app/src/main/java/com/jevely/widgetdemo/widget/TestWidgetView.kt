package com.jevely.widgetdemo.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

class TestWidgetView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private val paint = Paint()

    private val path= Path()


    init {
        paint.isAntiAlias = true
        paint.color = Color.BLUE
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.textSize = 36f

        val text = "我是李嘉伟"
        val width = paint.measureText(text)
        val metricsInt = paint.fontMetricsInt
        Log.d("LJW", "top = ${metricsInt.top} | bottom = ${metricsInt.bottom}")
        val height = metricsInt.bottom - metricsInt.top
        Log.d("LJW","width = $width | height = $height")

        val rect = Rect()
        paint.getTextBounds(text, 0, text.length, rect)
        Log.d(
            "LJW",
            "top = ${rect.top} | right = ${rect.right} | bottom = ${rect.bottom} | left = ${rect.left}"
        )

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

}