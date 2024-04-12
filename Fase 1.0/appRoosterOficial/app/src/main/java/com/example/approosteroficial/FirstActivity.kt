package com.example.approosteroficial
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first) // Asegúrate de que el nombre del layout sea el correcto

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}

