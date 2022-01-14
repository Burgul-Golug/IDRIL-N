package com.example.idril.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.idril.MainActivity
import com.example.idril.R
import com.example.idril.adapter.BrandAdapter
import com.example.idril.data.DataSource.Brands
import com.example.idril.databinding.FragmentBrandBinding

class BrandsFragment : Fragment(R.layout.fragment_brand) {
    companion object{
        fun newInstance() = BrandsFragment()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentBrandBinding.bind(view)
        binding.rvBrands.layoutManager = LinearLayoutManager(requireContext())
        binding.rvBrands.adapter = BrandAdapter(Brands) {
            (activity as MainActivity).navigateToFragment(ProductsFragment.newInstance())
        }
        binding.icClose.setOnClickListener {
            (activity as MainActivity).navigateToFragment(AuthorizationFragment.newInstance())
        }
        binding.icProfile.setOnClickListener {
            (activity as MainActivity).navigateToFragment(ProfileFragment.newInstance())
        }
    }
}
