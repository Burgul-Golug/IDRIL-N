package com.example.idril.network

import com.example.idril.model.Brand
import com.example.idril.model.Product
import com.example.idril.model.Review
import retrofit2.http.GET

interface RestApi {
    @GET("brands")
    suspend fun loadBrands(): List<Brand>
    @GET("products")
    suspend fun loadProducts(): List<Product>
    @GET("reviews")
    suspend fun loadReviews(): List<Review>

}