package com.example.idril.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.idril.MainActivity
import com.example.idril.R
import com.example.idril.adapter.ProductAdapter
import com.example.idril.databinding.FragmentProductsBinding
import com.example.idril.network.NetworkService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import kotlinx.serialization.ExperimentalSerializationApi

class ProductsFragment : Fragment(R.layout.fragment_products) {

    private lateinit var binding: FragmentProductsBinding
    private val coroutineExceptionHandler = CoroutineExceptionHandler{ context,exception ->
        binding.progressBar.visibility = View.GONE
        binding.rvProduct.adapter =
            ProductAdapter(listOf()) {}
        binding.RefreshProducts.isRefreshing = false
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
        fun newInstance() = ProductsFragment()
    }
    @ExperimentalSerializationApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductsBinding.bind(view)
        binding.icClose.setOnClickListener {
            (activity as MainActivity).navigateToFragment(BrandsFragment.newInstance())
        }
        binding.icProfile.setOnClickListener {
            (activity as MainActivity).navigateToFragment(ProfileFragment.newInstance())
        }

        loadProducts()

        binding.RefreshProducts.setOnRefreshListener {
            binding.RefreshProducts.isRefreshing = true
            loadProducts()
            binding.RefreshProducts.isRefreshing = false
        }
    }

    @ExperimentalSerializationApi
    private fun loadProducts() {
        scope.launch {
            val products = NetworkService.loadProducts()
            binding.rvProduct.layoutManager = LinearLayoutManager(context)
            binding.rvProduct.adapter =
                ProductAdapter(products) {
                    (activity as MainActivity).navigateToFragment(
                        ReviewsFragment.newInstance())
                }
            binding.progressBar.visibility = View.GONE
            binding.RefreshProducts.isRefreshing = false
        }
    }
}