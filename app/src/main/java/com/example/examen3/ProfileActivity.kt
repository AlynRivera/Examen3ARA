package com.example.examen3  // ← ajusta a tu paquete

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ProfileActivity : AppCompatActivity() {

    private lateinit var tvNombreJugador: TextView
    private lateinit var tvUltimaConexion: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        tvNombreJugador = findViewById(R.id.tvNombreJugador)
        tvUltimaConexion = findViewById(R.id.tvUltimaConexion)

        val prefs = getSharedPreferences("perfil_jugador", Context.MODE_PRIVATE)

        val ultimaConexion = prefs.getString("ultima_conexion", "Sin conexiones previas")
        tvUltimaConexion.text = "Última conexión: $ultimaConexion"

        val formatoFecha = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        val fechaActual = formatoFecha.format(Date())
        prefs.edit().putString("ultima_conexion", fechaActual).apply()
    }
}