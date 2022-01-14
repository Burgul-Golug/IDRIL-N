package com.example.idril.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.idril.MainActivity
import com.example.idril.R
import com.example.idril.databinding.FragmentAuthorizationBinding

class AuthorizationFragment : Fragment(R.layout.fragment_authorization) {
    companion object{
        fun newInstance() = AuthorizationFragment()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAuthorizationBinding.bind(view)
        binding.Login.setOnClickListener{
            (activity as MainActivity).navigateToFragment(BrandsFragment.newInstance())
        }
    }
}