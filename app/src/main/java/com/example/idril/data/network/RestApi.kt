package com.example.idril.data.network

import com.example.idril.domain.model.Brand
import com.example.idril.domain.model.Product
import com.example.idril.domain.model.Review
import retrofit2.http.GET

interface RestApi {
    @GET("brands")
    suspend fun loadBrands(): List<Brand>
    @GET("products")
    suspend fun loadProducts(): List<Product>
    @GET("reviews")
    suspend fun loadReviews(): List<Review>

}