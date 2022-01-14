package com.example.idril.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.idril.MainActivity
import com.example.idril.R
import com.example.idril.adapter.ReviewAdapter
import com.example.idril.databinding.FragmentReviewsBinding
import com.example.idril.network.NetworkService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import kotlinx.serialization.ExperimentalSerializationApi

class ReviewsFragment : Fragment(R.layout.fragment_reviews) {

    private lateinit var binding: FragmentReviewsBinding
    private val coroutineExceptionHandler = CoroutineExceptionHandler{ context,exception ->
        binding.progressBar.visibility = View.GONE
        binding.rvReview.adapter =
            ReviewAdapter(listOf()) {}
        binding.RefreshReviews.isRefreshing = false
        Snackbar.make(
            requireView(),
            getString(R.string.error),
            Snackbar.LENGTH_SHORT
        ).setBackgroundTint(Color.parseColor("#ED4337"))
            .setActionTextColor(Color.parseColor("#FFFFFF"))
            .show()
    }
    private val scope =
        CoroutineScope(Dispatchers.Main + SupervisorJob() + coroutineExceptionHandler)

    companion object{
        fun newInstance() = ReviewsFragment()
    }
    @ExperimentalSerializationApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentReviewsBinding.bind(view)
        binding.icClose.setOnClickListener {
            (activity as MainActivity).navigateToFragment(ProductsFragment.newInstance())
        }
        binding.icProfile.setOnClickListener {
            (activity as MainActivity).navigateToFragment(ProfileFragment.newInstance())
        }

        loadReviews()

        binding.RefreshReviews.setOnRefreshListener {
            binding.RefreshReviews.isRefreshing = true
            loadReviews()
            binding.RefreshReviews.isRefreshing = false
        }
    }

    @ExperimentalSerializationApi
    private fun loadReviews() {
        scope.launch {
            val reviews = NetworkService.loadReviews()
            binding.rvReview.layoutManager = LinearLayoutManager(context)
            binding.rvReview.adapter = ReviewAdapter(reviews) {}
            binding.progressBar.visibility = View.GONE
            binding.RefreshReviews.isRefreshing = false
        }
    }
}