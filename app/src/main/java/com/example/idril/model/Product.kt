package com.example.idril.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val product_image_URL: String,
    val product_name : String,
    val product_price: String,
    val product_color: String,
    val product_size: String,
)