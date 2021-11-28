package com.example.agrolibre.model

import android.database.sqlite.SQLiteDatabase
import androidx.fragment.app.FragmentActivity
import android.database.sqlite.SQLiteOpenHelper



class DBHelper(context: FragmentActivity):
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


        companion object{
            private val DATABASE_VERSION = 1
            private val DATABASE_NAME = "info"
            private val TABLE = "Admin"
        }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE "+ TABLE+ " (" +
                "id"+" INTEGER  PRIMARY KEY AUTOINCREMENT, "+
                "nombre"+" TEXT NOT NULL, "+
                "direccion"+" TEXT NOT NULL, "+
                "correo"+ " TEXT NOT NULL, "+
                "telefono"+ "TEXT NOT NULL)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}