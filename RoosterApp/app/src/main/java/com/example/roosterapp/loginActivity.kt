package com.example.roosterapp
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val videoView = findViewById<VideoView>(R.id.videoView)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val registerButton = findViewById<Button>(R.id.registerButton)

        // Establecer el video de fondo
        videoView.setVideoPath("android.resource://" + packageName + "/" + R.raw.video)
        videoView.start()

        // Configurar clics de botón
        loginButton.setOnClickListener {
            // Aquí debes agregar la lógica para iniciar sesión
            // Si los datos son válidos, puedes iniciar la actividad principal
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Esto evita que el usuario vuelva atrás con el botón de atrás
        }

        registerButton.setOnClickListener {
            // Navegar a la pantalla de registro
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
