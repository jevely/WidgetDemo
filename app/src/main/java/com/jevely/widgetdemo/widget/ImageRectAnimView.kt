package com.jevely.widgetdemo.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.jevely.widgetdemo.R
import kotlin.concurrent.thread

/**
 * 利用Region区域转换成Path
 * 在使用clipPath切割Canvas
 * 最后呈现出动画
 */
class ImageRectAnimView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val bitmap = BitmapFactory.decodeResource(resources, R.drawable.testimage)

    val bitmapPaint = Paint()

    var animWidth = 0

    var newViewWidth = 0
    var newViewHeight =0

    private var colorRegion : Region

    init {
        bitmapPaint.isAntiAlias = true
        newViewWidth = bitmap.width
        newViewHeight = bitmap.height
        colorRegion = Region(0, 100, newViewWidth, newViewHeight)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawBitmap(canvas)


    }

    private fun drawBitmap(canvas: Canvas?) {
        if (canvas == null) return
        canvas.drawColor(Color.BLACK)
        val whiteRegion1 = Region(0, 0, animWidth, 50)
        val whiteRegion2 = Region(newViewWidth - animWidth, 50, newViewWidth, 100)

        whiteRegion1.op(whiteRegion2, Region.Op.UNION)
        val success2 = whiteRegion1.op(colorRegion, Region.Op.UNION)

        if (success2) {
            Log.d("LJW", "合并成功")
            val path = whiteRegion1.boundaryPath
            //取并集
            canvas.clipPath(path, Region.Op.INTERSECT)
            canvas.drawColor(Color.RED)
            canvas.drawBitmap(bitmap, 0f, 0f, bitmapPaint)
        } else {
            canvas.drawBitmap(bitmap, 0f, 0f, bitmapPaint)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(newViewWidth, newViewHeight)
    }

    fun startAnim() {
        animWidth += 100
        if (animWidth >= newViewWidth) {
            animWidth = newViewWidth
        }
        invalidate()
    }

    fun autoAnim() {
        thread {
            while (animWidth < newViewWidth) {
                animWidth += 5
                Thread.sleep(16)
                invalidate()
            }

            if (animWidth >= newViewWidth) {
                animWidth = newViewWidth
            }
            invalidate()
        }
    }

}