package com.example.examen3

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "jugadores.db"
        const val DATABASE_VERSION = 1
        const val TABLE_JUGADORES = "jugadores"
        const val COLUMN_ID = "id"
        const val COLUMN_NOMBRE = "nombre"
        const val COLUMN_CONTRASENA = "contrasena"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_JUGADORES (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NOMBRE TEXT NOT NULL,
                $COLUMN_CONTRASENA TEXT NOT NULL
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_JUGADORES")
        onCreate(db)
    }

    fun registrarJugador(nombre: String, contrasena: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE, nombre)
            put(COLUMN_CONTRASENA, contrasena)
        }
        val resultado = db.insert(TABLE_JUGADORES, null, values)
        db.close()
        return resultado != -1L
    }
    fun validarJugador(nombre: String, contrasena: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_JUGADORES WHERE $COLUMN_NOMBRE = ? AND $COLUMN_CONTRASENA = ?"
        val cursor = db.rawQuery(query, arrayOf(nombre, contrasena))
        val existe = cursor.count > 0
        cursor.close()
        db.close()
        return existe
    }
}
