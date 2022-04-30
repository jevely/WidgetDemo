package com.jevely.widgetdemo.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class LoadingCircleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint()
    private val path = Path()
    private var currentAnimatorValue = 0f
    private var pathMeasure = PathMeasure()
    private val drawPath = Path()
    private var startD = 0f
    private var stopD = 0f

    init {
        paint.isAntiAlias = true
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f

        path.addCircle(100f, 100f, 48f, Path.Direction.CW)
        pathMeasure.setPath(path, true)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.WHITE)

        startD = if (currentAnimatorValue < 0.5) {
            0f
        } else {
            //0.5 - 1
            //0 - pathMeasure.length
            pathMeasure.length * (currentAnimatorValue - 0.5f) * 2
        }

        stopD = pathMeasure.length * currentAnimatorValue

        drawPath.reset()
        pathMeasure.getSegment(startD, stopD, drawPath, true)
        canvas?.drawPath(drawPath, paint)
    }

    /**
     * 开始动画
     */
    fun startAnim() {
        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.repeatCount = ValueAnimator.INFINITE
        animator.duration = 800
        animator.addUpdateListener {
            currentAnimatorValue = it.animatedValue as Float
            postInvalidate()
        }
        animator.start()
    }

}