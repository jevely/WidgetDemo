package com.jevely.widgetdemo.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * 渐变的文字
 */
class GradualTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val textPaint = Paint()
    private val content = "我是真的牛逼!!!"
    private var textWidth = 0f
    private var textHeight = 0f
    private var moveTextY = 0f

    init {
        textPaint.isAntiAlias = true
        textPaint.textSize = 42f
        textPaint.color = Color.WHITE
        textPaint.isDither = true
        textPaint.strokeWidth = 3f

        textWidth = textPaint.measureText(content)
        val fontMetrics = textPaint.fontMetrics
        textHeight = fontMetrics.bottom - fontMetrics.top
        moveTextY = -fontMetrics.top

        val linearGradient = LinearGradient(
            0f, 0f, textWidth, textHeight, intArrayOf(Color.WHITE, Color.RED, Color.WHITE),
            floatArrayOf(0.0f, 0.5f, 1.0f), Shader.TileMode.CLAMP
        )
        textPaint.shader = linearGradient
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.BLACK)
        canvas?.drawText(content, 0f, moveTextY, textPaint)
        canvas?.drawLine(0f, textHeight, textWidth, textHeight, textPaint)

    }

}