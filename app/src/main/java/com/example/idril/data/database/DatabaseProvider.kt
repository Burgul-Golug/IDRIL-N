package com.example.idril.data.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    private var db: BrandsDatabase? = null

    fun provideDatabase(context: Context): BrandsDatabase {
        return db ?: Room.databaseBuilder(
            context.applicationContext,
            BrandsDatabase::class.java, "brands_app_database"
        )
            .build()
            .also{ db = it }
    }
}