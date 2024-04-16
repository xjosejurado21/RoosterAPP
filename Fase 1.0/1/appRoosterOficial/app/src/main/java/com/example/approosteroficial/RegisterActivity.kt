package com.example.roosterapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.approosteroficial.R
import com.google.firebase.firestore.FirebaseFirestore
import java.security.MessageDigest

class RegisterActivity : AppCompatActivity() {

    private val TAG = "RegisterActivity"
    private lateinit var db: FirebaseFirestore // Firestore database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inicializar Firestore
        db = FirebaseFirestore.getInstance()

        // Obtener referencias a los elementos de la interfaz de usuario
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText) // Campo para el correo electrónico
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val confirmPasswordEditText = findViewById<EditText>(R.id.confirmPasswordEditText)
        val registerButton = findViewById<Button>(R.id.registerButton)

        // Configurar el listener para el botón de registro
        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()

            // Verificar si algún campo está vacío
            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Verificar si las contraseñas coinciden
            if (password != confirmPassword) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Registro del usuario utilizando Firestore
            registerUser(username, email, password)
        }
    }

    private fun registerUser(username: String, email: String, password: String) {
        // Encriptar la contraseña antes de almacenarla
        val encryptedPassword = encryptPassword(password)

        // Preparar el documento del usuario con la contraseña encriptada
        val user = hashMapOf(
            "username" to username,
            "email" to email,
            "password" to encryptedPassword
        )

        // Utilizar el correo electrónico como clave única para el documento del usuario
        db.collection("users").document(email)
            .set(user)
            .addOnSuccessListener {
                Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show()
                finish() // Finaliza la actividad de registro y redirige al usuario
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error al registrar el usuario en Firestore", e)
                Toast.makeText(this, "Error al registrar: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun encryptPassword(password: String): String {
        // Encriptar la contraseña utilizando SHA-256
        val bytes = MessageDigest.getInstance("SHA-256").digest(password.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}

