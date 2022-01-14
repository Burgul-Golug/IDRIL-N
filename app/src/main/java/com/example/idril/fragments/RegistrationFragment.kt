package com.example.idril.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.idril.R
import com.example.idril.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment(R.layout.fragment_registration) {
    companion object{
        fun newInstance() = RegistrationFragment()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentRegistrationBinding.bind(view)
    }
}