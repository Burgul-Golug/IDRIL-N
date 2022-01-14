package com.example.idril.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.idril.R
import com.example.idril.model.Brand

typealias OnBrandClickListener = (Brand) -> Unit

class BrandAdapter (
    private val brands : List<Brand>,
    private val listener : OnBrandClickListener,
) : RecyclerView.Adapter<BrandAdapter.BrandVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BrandVH(
            layoutInflater.inflate(R.layout.item_brand, parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: BrandVH, position: Int) =
        holder.bind(brands[position])

    override fun getItemCount(): Int = brands.size


    class BrandVH(
        view: View,
        listener: OnBrandClickListener
    ) : RecyclerView.ViewHolder(view) {

        private val imageBrand = view.findViewById<ImageView>(R.id.imageBrand)
        private val nameBrand = view.findViewById<TextView>(R.id.nameBrand)
        private val descBrand = view.findViewById<TextView>(R.id.descBrand)
        private lateinit var brand: Brand

        init {
            view.setOnClickListener { listener(brand) }
        }

        fun bind(brand: Brand) {
            this.brand = brand
            nameBrand.text = brand.brand_name
            descBrand.text = brand.brand_desc
            Glide
                .with(itemView)
                .load(brand.brand_img_URL)
                .centerCrop()
                .placeholder(R.drawable.hanger)
                .into(imageBrand)
        }
    }
}