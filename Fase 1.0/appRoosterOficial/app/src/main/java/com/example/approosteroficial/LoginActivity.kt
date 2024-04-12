package com.example.approosteroficial

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.roosterapp.RegisterActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore
import java.security.MessageDigest

class LoginActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore // Firestore database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializar Firestore
        db = FirebaseFirestore.getInstance()

        val usernameEditText = findViewById<TextInputEditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<TextInputEditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val registerButton = findViewById<Button>(R.id.registerButton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, ingresa todos los campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Verificar el nombre de usuario y la contraseña en Firestore
            verifyUser(username, password)
        }

        registerButton.setOnClickListener {
            // Navegar a RegisterActivity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun verifyUser(username: String, password: String) {
        val encryptedPassword = encryptPassword(password)
        db.collection("users").whereEqualTo("username", username).get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    Toast.makeText(this, "Nombre de usuario o contraseña incorrectos.", Toast.LENGTH_SHORT).show()
                } else {
                    var isLoginSuccessful = false
                    for (document in documents) {
                        if (document.getString("password") == encryptedPassword) {
                            isLoginSuccessful = true
                            break
                        }
                    }
                    if (isLoginSuccessful) {
                        // Inicio de sesión exitoso
                        Toast.makeText(this, "Inicio de sesión exitoso.", Toast.LENGTH_SHORT).show()
                        navigateToNextScreen()
                    } else {
                        Toast.makeText(this, "Nombre de usuario o contraseña incorrectos.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .addOnFailureListener { e ->
                Log.w("LoginActivity", "Error al buscar usuario", e)
                Toast.makeText(this, "Error al iniciar sesión: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun encryptPassword(password: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(password.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }

    private fun navigateToNextScreen() {
        // Aquí se coloca el código para navegar a la siguiente pantalla
        val intent = Intent(this, FirstActivity::class.java) // Cambia MainActivity por la actividad destino
        startActivity(intent)
        finish()
    }
}
