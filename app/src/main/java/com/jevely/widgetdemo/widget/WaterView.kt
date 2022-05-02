package com.jevely.widgetdemo.widget

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator

/**
 * 录音水波纹动画
 */
class WaterView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val circlePaint = Paint()

    private val maxValueCount = 4
    private val maxValue = 1.6f * maxValueCount

    private var viewWidth = 180
    private var viewHeight = 164
    private val lineWidth = 24
    private val margin = 12
    private val lineCount = 5
    private val animTime = 2000L * maxValueCount

    private var currentAnimProgress = 0.0f
    private var isStartAnim = false
    private val path = Path()

    private val backYellow = Color.parseColor("#FDB042")
    private val backLightYellow = Color.parseColor("#FFD738")

    private var valueAnimator: ValueAnimator? = null

    init {
        viewWidth = lineWidth * lineCount + margin * (lineCount - 1) + margin

        circlePaint.isAntiAlias = true
        circlePaint.color = backLightYellow
        circlePaint.style = Paint.Style.FILL
        circlePaint.strokeCap = Paint.Cap.ROUND
        circlePaint.strokeWidth = lineWidth.toFloat()

        initPath()
    }

    private fun initPath() {
        path.reset()
        for (i in 0 until 5) {

            val startX = (margin / 2.0) + ((lineWidth + margin) * i).toFloat()
            val endX = startX + lineWidth

            val canDrawHeight = viewHeight - lineWidth

            val normalSize = 0.2f

            val currentLineHeight = canDrawHeight * normalSize
            val startY = (viewHeight - currentLineHeight) / 2.0
            val endY = (viewHeight - currentLineHeight) / 2.0 + currentLineHeight
            path.addRect(
                startX.toFloat(),
                startY.toFloat(),
                endX.toFloat(),
                endY.toFloat(),
                Path.Direction.CW
            )

            path.addCircle(
                (startX + (endX - startX) / 2.0).toFloat(),
                startY.toFloat(),
                lineWidth / 2.0f,
                Path.Direction.CW
            )

            path.addCircle(
                (startX + (endX - startX) / 2.0).toFloat(),
                endY.toFloat(),
                lineWidth / 2.0f,
                Path.Direction.CW
            )
        }
    }

    private fun updatePath(currentPlayTime: Long?) {

        var cc = (currentAnimProgress * 10 / 16 - 1).toInt()
        if(cc < 0){
            cc = 0
        }

        path.reset()
        for (i in 0 until 5) {

            val startX = (margin / 2.0) + ((lineWidth + margin) * i).toFloat()
            val endX = startX + lineWidth

            val canDrawHeight = viewHeight - lineWidth

            var normalSize = 0.2f

            normalSize -= i * 0.8f / 5

            var a = currentAnimProgress - 1.6f * cc
            if (a > (1.6f + i * 0.8f / 5)) {
                a -= 1.6f
            }

            if (normalSize + a <= 1) {
                normalSize += a
            } else {
                normalSize = 1 - ((normalSize + a) - 1)
            }

            if (normalSize <= 0.2f) {
                normalSize = 0.2f
            }

            val currentLineHeight = canDrawHeight * normalSize
            val startY = (viewHeight - currentLineHeight) / 2.0
            val endY = (viewHeight - currentLineHeight) / 2.0 + currentLineHeight
            path.addRect(
                startX.toFloat(),
                startY.toFloat(),
                endX.toFloat(),
                endY.toFloat(),
                Path.Direction.CW
            )

            path.addCircle(
                (startX + (endX - startX) / 2.0).toFloat(),
                startY.toFloat(),
                lineWidth / 2.0f,
                Path.Direction.CW
            )

            path.addCircle(
                (startX + (endX - startX) / 2.0).toFloat(),
                endY.toFloat(),
                lineWidth / 2.0f,
                Path.Direction.CW
            )
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.GREEN)
        canvas?.drawPath(path, circlePaint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(viewWidth, viewHeight)
    }

    /**
     * 倒计时动画
     */
    fun startAnim() {
        if (valueAnimator?.isRunning == true) {
            valueAnimator?.cancel()
            valueAnimator = null
        }
        valueAnimator = ValueAnimator.ofFloat(0f, maxValue)
        valueAnimator?.let {
            it.duration = animTime.toLong()
            it.interpolator = LinearInterpolator()
            it.addUpdateListener { value ->

                currentAnimProgress = value.animatedValue as Float
                updatePath(valueAnimator?.currentPlayTime)
                postInvalidate()
            }

            it.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) {

                }

                override fun onAnimationEnd(animation: Animator?) {

                }

                override fun onAnimationCancel(animation: Animator?) {

                }

                override fun onAnimationRepeat(animation: Animator?) {

                }

            })
            it.start()
        }
    }

    fun stopAnim() {
        if (valueAnimator?.isRunning == true) {
            valueAnimator?.cancel()
            valueAnimator = null
        }
    }

    fun pauseAnim() {
        if (valueAnimator?.isRunning == true) {
            valueAnimator?.pause()
        }
    }

    fun resumeAnim() {
        if (valueAnimator?.isPaused == true) {
            valueAnimator?.resume()
        }
    }

}