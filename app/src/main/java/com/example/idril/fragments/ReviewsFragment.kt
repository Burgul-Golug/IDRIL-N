package com.example.idril.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.idril.MainActivity
import com.example.idril.R
import com.example.idril.adapter.ReviewAdapter
import com.example.idril.data.DataSource.Reviews
import com.example.idril.databinding.FragmentReviewsBinding

class ReviewsFragment : Fragment(R.layout.fragment_reviews) {
    companion object{
        fun newInstance() = ReviewsFragment()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentReviewsBinding.bind(view)
        binding.rvReview.layoutManager = LinearLayoutManager(requireContext())
        binding.rvReview.adapter = ReviewAdapter(Reviews) {}
        binding.icClose.setOnClickListener {
            (activity as MainActivity).navigateToFragment(ProductsFragment.newInstance())
        }
        binding.icProfile.setOnClickListener {
            (activity as MainActivity).navigateToFragment(ProfileFragment.newInstance())
        }

    }
}