package com.example.examen3  //

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var etNombreUsuarioRegistro: EditText
    private lateinit var etContrasenaRegistro: EditText
    private lateinit var btnRegistrarse: Button
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        etNombreUsuarioRegistro = findViewById(R.id.etNombreUsuarioRegistro)
        etContrasenaRegistro = findViewById(R.id.etContrasenaRegistro)
        btnRegistrarse = findViewById(R.id.btnRegistrarse)
        dbHelper = DatabaseHelper(this)

        btnRegistrarse.setOnClickListener {
            val nombre = etNombreUsuarioRegistro.text.toString().trim()
            val contrasena = etContrasenaRegistro.text.toString().trim()

            if (nombre.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val exito = dbHelper.registrarJugador(nombre, contrasena)

            if (exito) {
                Toast.makeText(this, "Jugador registrado exitosamente", Toast.LENGTH_SHORT).show()
                etNombreUsuarioRegistro.text.clear()
                etContrasenaRegistro.text.clear()
            } else {
                Toast.makeText(this, "Error al registrar jugador", Toast.LENGTH_SHORT).show()
            }
        }
    }
}