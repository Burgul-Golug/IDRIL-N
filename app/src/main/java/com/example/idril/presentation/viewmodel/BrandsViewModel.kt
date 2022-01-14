package com.example.idril.presentation.viewmodel

import android.content.Context
import com.example.idril.data.network.NetworkService
import com.example.idril.presentation.ScreenState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi

class BrandsViewModel (
    private val context: Context,
    private val coroutineScope: CoroutineScope
) {
    private val _screenState = MutableStateFlow<ScreenState> (ScreenState.Loading)
    val screenState : StateFlow<ScreenState> = _screenState
    private var job: Job? = null

    @ExperimentalSerializationApi
    fun loadData() {
        job?.cancel()
        job = coroutineScope.launch {
            try {
                _screenState.emit(ScreenState.Loading)
                val brands = NetworkService.loadBrands()
                _screenState.emit(ScreenState.DataLoaded(brands))
            } catch ( ex: Throwable) {
                _screenState.emit(ScreenState.Error("Внимание, ошибка!!!"))
            }
        }
    }
}