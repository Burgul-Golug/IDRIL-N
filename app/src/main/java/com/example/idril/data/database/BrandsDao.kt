package com.example.idril.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.idril.domain.model.Brand

@Dao
interface BrandsDao {
    @Query("SELECT * FROM brand")
    suspend fun getAll(): List<Brand>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(headphones: List<Brand>)
}