package com.jevely.widgetdemo

import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.jevely.widgetdemo.widget.ImageRectAnimView
import com.jevely.widgetdemo.widget.LoadingCircleView
import com.jevely.widgetdemo.widget.TestObjectAnimatorView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val a = findViewById<LoadingCircleView>(R.id.circleView)
        findViewById<Button>(R.id.bt).setOnClickListener {
            a.startAnim()
        }

    }
}