package com.jevely.widgetdemo.widget

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.jevely.widgetdemo.R

class ShapeDrawableViewMagnifier @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val viewWidth = 1080
    private val viewHeight = 1920

    private val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.testimage)
    private val shapeDrawable = ShapeDrawable(OvalShape())

    private val paint = Paint()

    private val circleR = 80
    private val magnifier = 3

    private val shapeMatrix = Matrix()

    init {
        paint.isAntiAlias = true
        paint.isDither = true

        shapeDrawable.setBounds(0, 0, circleR * 2, circleR * 2)
        val magnifierBitmap =
            Bitmap.createScaledBitmap(bitmap, viewWidth * magnifier, viewHeight * magnifier, false)
        val bitmapShader =
            BitmapShader(magnifierBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        shapeDrawable.paint.shader = bitmapShader
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)


        canvas?.drawBitmap(bitmap, 0f, 0f, paint)
        shapeDrawable.draw(canvas!!)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(viewWidth, viewHeight)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {

            }
            MotionEvent.ACTION_MOVE -> {

                shapeMatrix.setTranslate(
                    circleR - event.x * magnifier,
                    circleR - event.y * magnifier
                )
                shapeDrawable.paint.shader.setLocalMatrix(shapeMatrix)

                shapeDrawable.setBounds(
                    (event.x - circleR).toInt(), (event.y - circleR).toInt(),
                    (event.x + circleR).toInt(), (event.y + circleR).toInt()
                )
                invalidate()
            }

        }
        return true
    }


}