package com.jevely.widgetdemo.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * xfermode测试
 */
class XfermodeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint()
    private var srcBitmap: Bitmap? = null
    private var dstBitmap: Bitmap? = null
    private val imgWidth = 200f
    private val imgHeight = 200f


    init {
        paint.isAntiAlias = true
        paint.isDither = true
        srcBitmap = getSrcBitmap()
        dstBitmap = getDstBitmap()
    }

    private fun getSrcBitmap(): Bitmap {
        val bitmap =
            Bitmap.createBitmap(imgWidth.toInt(), imgHeight.toInt(), Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val p = Paint(Paint.ANTI_ALIAS_FLAG)
        p.color = Color.BLUE
        canvas.drawRect(0f, 0f, imgWidth, imgHeight, p)
        return bitmap
    }

    private fun getDstBitmap(): Bitmap {
        val bitmap =
            Bitmap.createBitmap(imgWidth.toInt(), imgHeight.toInt(), Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val p = Paint(Paint.ANTI_ALIAS_FLAG)
        p.color = Color.GREEN
        canvas.drawOval(0f, 0f, imgWidth, imgHeight, p)
        return bitmap
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        val a =
            canvas?.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null, Canvas.ALL_SAVE_FLAG)

        canvas?.drawBitmap(dstBitmap!!, 0f, 0f, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        canvas?.drawBitmap(srcBitmap!!, 100f, 100f, paint)
        paint.xfermode = null

        canvas?.restoreToCount(a!!)
    }

}