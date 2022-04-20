package com.jevely.widgetdemo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.concurrent.thread

/**
 * 歌词渐变文字
 */
class KnowledgeTabTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var textPaint: Paint = Paint()
    private var textPaint2: Paint = Paint()
    private var linePaint = Paint()
    private var textHeight = 0f
    private var textWidth = 0f
    private var rectHeight = 0f

    private var animRegion: RectF? = null

    private var animWidth = 0f

    init {
        textPaint.isAntiAlias = true
        textPaint.isDither = true
        textPaint.textSize = 50f
        textPaint.color = Color.BLACK

        textPaint2.isAntiAlias = true
        textPaint2.isDither = true
        textPaint2.textSize = 50f
        textPaint2.color = Color.RED

        linePaint.isAntiAlias = true
        linePaint.style = Paint.Style.FILL
        linePaint.strokeWidth = 2f

        textHeight = getTextHeight()
        rectHeight = getAllHeight()
        getTextWidth()

        animRegion = RectF(0f, 0f, animWidth, rectHeight)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawText("我今天就是开始测试了", 0f, textHeight, textPaint)
        canvas?.clipRect(animRegion!!)
        canvas?.drawText("我今天就是开始测试了", 0f, textHeight, textPaint2)
    }

    fun getTextHeight(): Float {
        val fontMetrics = textPaint.fontMetrics
        return -fontMetrics.ascent
    }

    fun getAllHeight(): Float {
        val fontMetrics = textPaint.fontMetrics
        return fontMetrics.descent - fontMetrics.ascent
    }

    fun getTextWidth() {
        textWidth = textPaint.measureText("我今天就是开始测试了")
    }

    fun changeWidth() {
        animWidth = 0f
        thread {
            while (animWidth < textWidth) {
                Thread.sleep(16)
                animWidth += 2
                animRegion = RectF(0f, 0f, animWidth, rectHeight)
                postInvalidate()
            }
        }
    }

}