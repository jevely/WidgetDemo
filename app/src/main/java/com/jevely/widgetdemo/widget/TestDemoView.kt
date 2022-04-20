package com.jevely.widgetdemo.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class TestDemoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val paint = Paint()

    init {
        paint.isAntiAlias = true //打开抗锯齿

        paint.style = Paint.Style.FILL //内部填充
        paint.style = Paint.Style.FILL_AND_STROKE //内部填充和描边
        paint.style = Paint.Style.STROKE //描边

        paint.strokeWidth = 10f //描边宽度，只有style是FILL_AND_STROKE或者STROKE时有用

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }

}