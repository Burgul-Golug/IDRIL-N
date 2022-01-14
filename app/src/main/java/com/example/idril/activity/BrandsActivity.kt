package com.example.idril.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.idril.R
import com.example.idril.adapter.BrandAdapter
import com.example.idril.data.DataSource

class BrandsActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brand)
        val rvBrands = findViewById<RecyclerView>(R.id.rvBrands)
        rvBrands.layoutManager = LinearLayoutManager(this)
        rvBrands.adapter = BrandAdapter(DataSource.Brands) {
            val intent = Intent(this, ProductsActivity::class.java)
            startActivity(intent)
        }
    }
}
