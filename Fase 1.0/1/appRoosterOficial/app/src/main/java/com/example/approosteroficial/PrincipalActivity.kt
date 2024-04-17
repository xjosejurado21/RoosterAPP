package com.example.approosteroficial

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class PrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        // Configurar los listeners para cada CardView
        findViewById<LinearLayout>(R.id.cardViewWeb).setOnClickListener {
            // Abrir un link externo para "Web"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tu-sitio-web.com"))
            startActivity(intent)
        }

        findViewById<LinearLayout>(R.id.cardViewRoosterHouse).setOnClickListener {
            // Abrir un link externo para "Rooster House"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.roosterhouse.com"))
            startActivity(intent)
        }

        findViewById<LinearLayout>(R.id.cardViewPerfilRooster).setOnClickListener {
            // Puedes abrir otra actividad o dejarlo sin acción si es "Próximamente"
            // TODO: Define la acción aquí
        }

        findViewById<LinearLayout>(R.id.cardViewCerrarSesion).setOnClickListener {
            // Manejar la lógica de cerrar sesión
            cerrarSesion()
        }
    }

    private fun cerrarSesion() {
        // Aquí colocas tu lógica para cerrar sesión
        // Por ejemplo, si usas Firebase Auth, sería algo así:
        // FirebaseAuth.getInstance().signOut()
        // Después rediriges al usuario a la pantalla de inicio de sesión:
        val loginIntent = Intent(this, LoginActivity::class.java)
        loginIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(loginIntent)
    }
}
