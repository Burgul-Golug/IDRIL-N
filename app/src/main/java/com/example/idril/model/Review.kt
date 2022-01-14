package com.example.idril.model

import kotlinx.serialization.Serializable

@Serializable
data class Review(
    val userName: String,
    val rating: String,
    val product_desc: String
)