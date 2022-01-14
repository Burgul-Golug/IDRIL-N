package com.example.idril.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.serialization.Serializable

@Serializable
@Entity(primaryKeys = ["brand_img_URL","brand_name","brand_desc"])
data class Brand(
    @ColumnInfo val brand_img_URL: String,
    @ColumnInfo val brand_name : String,
    @ColumnInfo val brand_desc : String
)