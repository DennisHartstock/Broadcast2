package com.example.broadcast2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar

    private val receiver = object : BroadcastReceiver() {

        override fun onReceive(p0: Context?, p1: Intent?) {
            if (p1?.action == "loaded") {
                val percent = p1.getIntExtra("percent", 0)
                progressBar.progress = percent
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.progressBar)

        val intentFilter = IntentFilter().apply {
            addAction("loaded")
        }
        registerReceiver(receiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}