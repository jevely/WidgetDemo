package com.jevely.widgetdemo

import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.jevely.widgetdemo.widget.WaterView
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val a = findViewById<WaterView>(R.id.waterView1)
        a.setOnClickListener {
            a.startAnim()
        }

    }

}