package com.example.idril.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.idril.R
import com.example.idril.ScreenState
import com.example.idril.network.NetworkService
import com.example.idril.onClickFlow
import com.example.idril.onRefreshFlow
import com.example.idril.*
import com.example.idril.adapter.BrandAdapter
import com.example.idril.databinding.FragmentBrandsBinding
import com.example.idril.model.Brand
import com.example.idril.model.Product
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.coroutines.flow.flatMapLatest

@ExperimentalCoroutinesApi
@ExperimentalSerializationApi
class BrandsFragment : Fragment(R.layout.fragment_brands)  {
    private lateinit var binding: FragmentBrandsBinding

    companion object{
        fun newInstance() = BrandsFragment()
    }

    private fun setLoading(isLoading: Boolean) = with(binding) {
        progressBar.isVisible = isLoading && !rvBrands.isVisible
        RefreshBrands.isRefreshing = isLoading && rvBrands.isVisible
    }

    private fun setData(brands: List<Brand>?) = with(binding){
        rvBrands.isVisible = brands != null
        binding.rvBrands.layoutManager = LinearLayoutManager(context)
        binding.rvBrands.adapter =
            BrandAdapter(brands ?: listOf()){
                (activity as MainActivity).navigateToFragment(
                    ProductsFragment.newInstance())
            }
        binding.icClose.setOnClickListener {
            (activity as MainActivity).navigateToFragment(
                AuthorizationFragment.newInstance()
            )
        }
        binding.icProfile.setOnClickListener {
            (activity as MainActivity).navigateToFragment(
                ProfileFragment.newInstance()
            )
        }
    }
    private fun setError(message: String?) = with(binding){
        ErrLayout.isVisible = message != null
        textError.text = message
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBrandsBinding.bind(view)
        merge(
            flowOf(Unit),
            binding.RefreshBrands.onRefreshFlow(),
            binding.buttonError.onClickFlow()
        )
            .flatMapLatest {loadBrands()}
            .distinctUntilChanged()
            .onEach{
                when(it){
                    is ScreenState.DataLoaded -> {
                        setLoading(false)
                        setError(null)
                        setData(it.brands)
                    }
                    is ScreenState.Error -> {
                        setLoading(false)
                        setError(it.error)
                        setData(null)
                    }
                    ScreenState.Loading -> {
                        setLoading(true)
                        setError(null)
                    }
                }
            }
            .launchIn(lifecycleScope)

    }
    @ExperimentalSerializationApi
    private fun loadBrands() = flow {

        emit(ScreenState.Loading)
        val brand = NetworkService.loadBrands()
        emit(ScreenState.DataLoaded(brand))
    }
        .catch{
            emit(ScreenState.Error(getString(R.string.error_connect)))
        }
}