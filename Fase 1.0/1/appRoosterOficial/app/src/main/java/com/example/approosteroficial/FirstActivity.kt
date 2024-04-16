package com.example.approosteroficial

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
 // Asegúrate de que la importación es correcta

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        val getStartedButton = findViewById<Button>(R.id.buttonGetStarted)
        getStartedButton.setOnClickListener {
            // Start LoginActivity when button is clicked
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}

