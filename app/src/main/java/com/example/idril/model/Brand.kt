package com.example.idril.model

import androidx.annotation.DrawableRes

data class Brand(
    @DrawableRes val brand_img:Int,
    val brand_name : String,
    val brand_desc : String
)