package com.example.idril.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.idril.R
import com.example.idril.model.Review

typealias OnReviewClickListener = (Review) -> Unit

class ReviewAdapter (
    private val reviews : List<Review>,
    private val listener : OnReviewClickListener,
) : RecyclerView.Adapter<ReviewAdapter.ReviewVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ReviewVH(
            layoutInflater.inflate(R.layout.item_product, parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: ReviewVH, position: Int) =
        holder.bind(reviews[position])

    override fun getItemCount(): Int = reviews.size


    class ReviewVH(
        view: View,
        listener: OnReviewClickListener
    ) : RecyclerView.ViewHolder(view) {

        private val user_name = view.findViewById<TextView>(R.id.User_name)
        private val rating = view.findViewById<TextView>(R.id.personRating)
        private val product_desc = view.findViewById<TextView>(R.id.comments_text)

        private lateinit var review: Review

        init {
            view.setOnClickListener { listener(review) }
        }

        fun bind(review: Review) {
            this.review = review
            user_name.text = review.user_name
            rating.text = review.rating
            product_desc.text = review.product_desc
        }
    }
}