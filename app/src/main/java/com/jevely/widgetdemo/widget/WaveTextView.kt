package com.jevely.widgetdemo.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.jevely.widgetdemo.R

class WaveTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint()
    private var textBitmap = BitmapFactory.decodeResource(resources, R.drawable.textimg)
    private var waveBitmap =
        Bitmap.createBitmap(textBitmap.width, textBitmap.height, Bitmap.Config.ARGB_8888)

    private var waveDx = 0

    private var wavePath = Path()

    init {
        paint.isAntiAlias = true
        paint.isDither = true
        paint.color = Color.GREEN

        setLayerType(View.LAYER_TYPE_SOFTWARE, null)

        startAnim()
    }

    private fun startAnim() {
        val valueAnimatorView = ValueAnimator.ofInt(0, waveBitmap.width)
        valueAnimatorView.duration = 2000
        valueAnimatorView.repeatCount = ValueAnimator.INFINITE
        valueAnimatorView.interpolator = LinearInterpolator()
        valueAnimatorView.addUpdateListener {
            waveDx = it.animatedValue as Int
            postInvalidate()
        }
        valueAnimatorView.start()
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

        //生成波纹图片
        createWavePath()

        val waveCanvas = Canvas(waveBitmap)
        waveCanvas.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR)
        waveCanvas.drawPath(wavePath, paint)

        //现绘制文字图片
        canvas?.drawBitmap(textBitmap, 0f, 0f, paint)

        //开始绘制混合模式
        canvas?.save()
        canvas?.drawBitmap(waveBitmap, 0f, 0f, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        canvas?.drawBitmap(textBitmap, 0f, 0f, paint)
        paint.xfermode = null
        canvas?.restore()
    }

    private fun createWavePath() {
        wavePath.reset()
        val waveHeight = waveBitmap.height / 2.0f
        val halfWaveWidth = waveBitmap.width / 2.0f
        wavePath.moveTo(-halfWaveWidth + waveDx, waveHeight)

        var i = -halfWaveWidth.toInt()
        while (i < (waveBitmap.width + halfWaveWidth).toInt()) {
            i += halfWaveWidth.toInt()
            wavePath.rQuadTo(halfWaveWidth / 2.0f, -50f, halfWaveWidth, 0f)
            wavePath.rQuadTo(halfWaveWidth / 2.0f, 50f, halfWaveWidth, 0f)
        }
        wavePath.lineTo(waveBitmap.width.toFloat(), waveBitmap.height.toFloat())
        wavePath.lineTo(0f, waveBitmap.height.toFloat())
        wavePath.close()
    }

}