package com.example.idril.presentation

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.idril.R
import com.example.idril.databinding.ActivityMainBinding
import com.example.idril.presentation.fragments.BrandsFragment

class MainActivity : FragmentActivity() {
        fun navigateToFragment(fmt: Fragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, fmt)
                .addToBackStack(fmt.javaClass.name)
                .commit()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        if (savedInstanceState == null) {
            navigateToFragment(BrandsFragment.newInstance())
        }
    }
}