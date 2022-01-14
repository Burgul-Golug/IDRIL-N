package com.example.idril

import com.example.idril.model.Brand

sealed class ScreenState {
        data class DataLoaded(val brands: List<Brand>) : ScreenState()
        object Loading : ScreenState()
        data class Error(val error: String) : ScreenState()
}
