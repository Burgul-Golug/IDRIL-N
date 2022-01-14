package com.example.idril.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.idril.R
import com.example.idril.domain.model.Product

typealias OnProductClickListener = (Product) -> Unit

class ProductAdapter (
    private val products : List<Product>,
    private val listener : OnProductClickListener,
) : RecyclerView.Adapter<ProductAdapter.ProductVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductVH(
            layoutInflater.inflate(R.layout.item_product, parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: ProductVH, position: Int) =
        holder.bind(products[position])

    override fun getItemCount(): Int = products.size


    class ProductVH(
        view: View,
        listener: OnProductClickListener
    ) : RecyclerView.ViewHolder(view) {

        private val imageProduct = view.findViewById<ImageView>(R.id.imageProduct)
        private val itemName = view.findViewById<TextView>(R.id.nameProduct)
        private val itemPrice = view.findViewById<TextView>(R.id.itemPrice)
        private val itemColor = view.findViewById<TextView>(R.id.ColorValue)
        private val itemSize = view.findViewById<TextView>(R.id.ItemSize)



        private lateinit var product: Product

        init {
            view.setOnClickListener { listener(product) }
        }

        fun bind(product: Product) {
            this.product = product
            itemName.text = product.product_name
            itemPrice.text = product.product_price
            itemColor.text = product.product_color
            itemSize.text = product.product_size
            Glide
                .with(itemView)
                .load(product.product_image_URL)
                .centerCrop()
                .placeholder(R.drawable.hanger)
                .into(imageProduct)
        }
    }
}