package com.jevely.widgetdemo.widget

import android.content.Context
import android.util.AttributeSet

/**
 * 自定义ObjectAnimator属性动画
 */
class TestObjectAnimatorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatImageView(context, attrs) {

    fun setDrag(dragX: Float) {
        translationX = dragX
    }

}