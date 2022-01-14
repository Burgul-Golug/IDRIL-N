package com.example.idril.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.idril.presentation.MainActivity
import com.example.idril.R
import com.example.idril.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    companion object{
        fun newInstance() = ProfileFragment()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentProfileBinding.bind(view)
        binding.icClose.setOnClickListener{
            (activity as MainActivity).navigateToFragment(BrandsFragment.newInstance())
        }
        binding.button.setOnClickListener{
            (activity as MainActivity).navigateToFragment(BrandsFragment.newInstance())
        }
    }
}