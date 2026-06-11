package com.example.examen3
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etNombreUsuario: EditText
    private lateinit var etContrasena: EditText
    private lateinit var btnIniciarSesion: Button
    private lateinit var btnRegistrar: Button
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNombreUsuario = findViewById(R.id.etNombreUsuario)
        etContrasena = findViewById(R.id.etContrasena)
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        dbHelper = DatabaseHelper(this)

        btnIniciarSesion.setOnClickListener {
            val nombre = etNombreUsuario.text.toString().trim()
            val contrasena = etContrasena.text.toString().trim()

            if (nombre.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val credencialesValidas = dbHelper.validarJugador(nombre, contrasena)

            if (credencialesValidas) {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Las credenciales no son correctas", Toast.LENGTH_SHORT).show()
            }
        }

        btnRegistrar.setOnClickListener {
            val nombre = etNombreUsuario.text.toString().trim()

            if (nombre.isEmpty()) {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
                return@setOnClickListener
            }

            val jugadorExiste = dbHelper.existeJugador(nombre)

            if (jugadorExiste) {
                Toast.makeText(this, "El usuario ya está registrado", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }
        }

    }
}