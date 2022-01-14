package com.example.idril.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.idril.domain.model.Brand

@Database(
    entities = [Brand::class],
    version = 1
)
abstract class BrandsDatabase : RoomDatabase() {
    abstract fun BrandDAO(): BrandsDao
}