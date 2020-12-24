package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnClickMe = findViewById<Button>(R.id.button)
        val myTextView = findViewById<TextView>(R.id.textView)
        var timeClicked =0
        btnClickMe.setOnClickListener {
            myTextView.text = timeClicked++.toString()
            Toast.makeText(this@MainActivity, "you clicked me", Toast.LENGTH_SHORT).show()
        }
    }


}