package com.example.agrolibre.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val direccion: String,
    val telefono: String,
    val email: String
)