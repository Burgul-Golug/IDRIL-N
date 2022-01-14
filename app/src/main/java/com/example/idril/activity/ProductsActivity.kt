package com.example.idril.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.idril.R
import com.example.idril.adapter.ProductAdapter
import com.example.idril.data.DataSource

class ProductsActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        val rvProducts = findViewById<RecyclerView>(R.id.rvProduct)
        rvProducts.layoutManager = LinearLayoutManager(this)
        rvProducts.adapter = ProductAdapter(DataSource.Products) {}
    }
}