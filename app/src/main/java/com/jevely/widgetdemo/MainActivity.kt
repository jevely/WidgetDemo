package com.jevely.widgetdemo

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.rect_anim_bt).setOnClickListener {
            val animView = findViewById<ImageRectAnimView>(R.id.rect_animview)
            animView.autoAnim()
        }
    }
}