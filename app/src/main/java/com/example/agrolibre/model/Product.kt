package com.example.agrolibre.model

data class Product(val imageUrl: String ="url",
                   val name: String = "nombre",
                   val price: Float = 10F,
                   val description: String = "descripcion"
)