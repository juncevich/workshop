package com.example.mycalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
        val tvInput: TextView = findViewById(R.id.tvInput)
        tvInput.append((view as Button).text)
    }

    fun onCLear(view: View) {
        val tvInput: TextView = findViewById(R.id.tvInput)
        tvInput.text = ""
    }
}