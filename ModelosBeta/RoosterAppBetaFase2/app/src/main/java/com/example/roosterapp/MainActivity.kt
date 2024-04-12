package com.example.roosterapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roosterapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar OnClickListener para cada CardView
        binding.apply {
            binding.web.setOnClickListener {
                // Abrir enlace externo a la web de Rooster
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://roosterbreak.com/"))
                startActivity(intent)
            }

            binding.novedades.setOnClickListener {
                // Abrir la actividad SecundariaActivity
                val intent = Intent(this@MainActivity, SecundariaActivity::class.java)
                startActivity(intent)
            }

           binding.quizGame.setOnClickListener {
                // Abrir la actividad QuizActivity
                val intent = Intent(this@MainActivity, QuizActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
