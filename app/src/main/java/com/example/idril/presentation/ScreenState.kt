package com.example.idril.presentation

import com.example.idril.domain.model.Brand

sealed class ScreenState {
        data class DataLoaded(val brands: List<Brand>) : ScreenState()
        object Loading : ScreenState()
        data class Error(val error: String) : ScreenState()
}
