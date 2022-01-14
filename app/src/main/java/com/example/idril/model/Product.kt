package com.example.idril.model

import androidx.annotation.DrawableRes

data class Product(
    @DrawableRes val product_image: Int,
    val product_name : String,
    val product_price: String,
    val product_color: String,
    val product_size: String,
)