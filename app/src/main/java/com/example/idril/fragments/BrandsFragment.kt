package com.example.idril.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.idril.MainActivity
import com.example.idril.R
import com.example.idril.adapter.BrandAdapter
import com.example.idril.databinding.FragmentBrandsBinding
import com.example.idril.network.NetworkService
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import kotlinx.serialization.ExperimentalSerializationApi

class BrandsFragment : Fragment(R.layout.fragment_brands) {

    private lateinit var binding: FragmentBrandsBinding
    private val coroutineExceptionHandler = CoroutineExceptionHandler{ context,exception ->
        exception.printStackTrace()
        binding.progressBar.visibility = GONE
        binding.rvBrands.adapter =
            BrandAdapter(listOf()) {}
        binding.RefreshBrands.isRefreshing = false
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
        fun newInstance() = BrandsFragment()
    }
    @ExperimentalSerializationApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBrandsBinding.bind(view)
        binding.icClose.setOnClickListener {
            (activity as MainActivity).navigateToFragment(AuthorizationFragment.newInstance())
        }
        binding.icProfile.setOnClickListener {
            (activity as MainActivity).navigateToFragment(ProfileFragment.newInstance())
        }

        loadBrands()

        binding.RefreshBrands.setOnRefreshListener {
            binding.RefreshBrands.isRefreshing = true
            loadBrands()
            binding.RefreshBrands.isRefreshing = false
        }
    }

    @ExperimentalSerializationApi
    private fun loadBrands() {
        scope.launch {
            val brands = NetworkService.loadBrands()
            binding.rvBrands.layoutManager = LinearLayoutManager(context)
            binding.rvBrands.adapter =
                BrandAdapter(brands) {
                    (activity as MainActivity).navigateToFragment(
                        ProductsFragment.newInstance())
                }
            binding.progressBar.visibility = GONE
            binding.RefreshBrands.isRefreshing = false
        }
    }
}
