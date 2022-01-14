package com.example.idril.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.idril.MainActivity
import com.example.idril.R
import com.example.idril.adapter.ProductAdapter
import com.example.idril.data.DataSource.Products
import com.example.idril.databinding.FragmentProductsBinding

class ProductsFragment : Fragment(R.layout.fragment_products) {
    companion object{
        fun newInstance() = ProductsFragment()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentProductsBinding.bind(view)
        binding.rvProduct.layoutManager = LinearLayoutManager(requireContext())
        binding.rvProduct.adapter = ProductAdapter(Products) {
            (activity as MainActivity).navigateToFragment(ReviewsFragment.newInstance())
        }
        binding.icClose.setOnClickListener {
            (activity as MainActivity).navigateToFragment(BrandsFragment.newInstance())
        }
        binding.icProfile.setOnClickListener {
            (activity as MainActivity).navigateToFragment(ProfileFragment.newInstance())
        }
    }
}