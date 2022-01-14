package com.example.idril.model

import kotlinx.serialization.Serializable

@Serializable
data class Brand(
    val brand_img_URL: String,
    val brand_name : String,
    val brand_desc : String
)