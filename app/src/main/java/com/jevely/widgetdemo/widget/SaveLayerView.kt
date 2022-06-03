package com.jevely.widgetdemo.widget

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.jevely.widgetdemo.R

class SaveLayerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint()

    private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.testimage)

    init {
        paint.isAntiAlias = true
        paint.isDither = true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawBitmap(bitmap, 0f, 0f, paint)

        val a = canvas?.saveLayerAlpha(0f, 0f, 200f, 200f, 100)
        canvas?.drawColor(Color.GRAY)
        canvas?.restoreToCount(a!!)

    }

}