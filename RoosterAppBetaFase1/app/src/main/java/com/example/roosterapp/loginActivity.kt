package com.example.roosterapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.security.MessageDigest

class LoginActivity : AppCompatActivity() {

    private val TAG = "LoginActivity"

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializar Firestore
        db = FirebaseFirestore.getInstance()

        // Obtener referencias a los elementos de la interfaz de usuario
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val registerButton = findViewById<Button>(R.id.registerButton)

        // Configurar el listener para el botón de inicio de sesión
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Verificar si algún campo está vacío
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Encriptar la contraseña
            val encryptedPassword = encryptPassword(password)

            // Buscar el usuario en Firestore
            db.collection("users")
                .whereEqualTo("username", username)
                .whereEqualTo("password", encryptedPassword)
                .get()
                .addOnSuccessListener { documents ->
                    if (documents.isEmpty) {
                        // No se encontró ningún usuario con las credenciales proporcionadas
                        Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                    } else {
                        // Se encontró el usuario, iniciar sesión
                        Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()

                        // Iniciar MainActivity
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish() // Cerrar LoginActivity para evitar que el usuario regrese atrás
                    }
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error al buscar usuario", e)
                    Toast.makeText(this, "Error al iniciar sesión: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

        // Configurar el listener para el botón de registro
        registerButton.setOnClickListener {
            // Navegar a la pantalla de registro
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun encryptPassword(password: String): String {
        // Encriptar la contraseña utilizando SHA-256
        val bytes = MessageDigest.getInstance("SHA-256").digest(password.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}
