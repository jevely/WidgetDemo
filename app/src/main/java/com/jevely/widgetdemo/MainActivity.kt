package com.jevely.widgetdemo

import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.jevely.widgetdemo.widget.ImageRectAnimView
import com.jevely.widgetdemo.widget.TestObjectAnimatorView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val a = findViewById<TestObjectAnimatorView>(R.id.objectView)
        findViewById<Button>(R.id.bt).setOnClickListener {
            val animator = ObjectAnimator.ofFloat(a,"drag",0f,200f)
            animator.duration = 1000
            animator.start()
        }

    }
}