package com.jevely.widgetdemo.widget

import android.content.Context
import android.util.AttributeSet

/**
 * 自定义ObjectAnimator属性动画
 */
class TestObjectAnimatorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatImageView(context, attrs) {

    /**
     * ObjectAnimator会通过反射调用该方法
     */
    fun setDrag(dragX: Float) {
        translationX = dragX
    }

}