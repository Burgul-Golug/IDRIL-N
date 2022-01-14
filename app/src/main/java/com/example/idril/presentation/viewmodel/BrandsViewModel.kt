package com.example.idril.presentation.viewmodel

import android.content.Context
import com.example.idril.data.database.DatabaseProvider
import com.example.idril.data.network.NetworkService
import com.example.idril.presentation.ScreenState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import java.io.IOException

class BrandsViewModel (
    private val context: Context,
    private val coroutineScope: CoroutineScope
) {
    private val brandsDAO = DatabaseProvider.provideDatabase(context).BrandDAO()
    private val _screenState = MutableStateFlow<ScreenState> (ScreenState.Loading)
    val screenState : StateFlow<ScreenState> = _screenState
    private var job: Job? = null

    @ExperimentalSerializationApi
    fun loadData() {
        job?.cancel()
        job = coroutineScope.launch {
            try {
                _screenState.value = ScreenState.Loading
                val brands = try {
                    NetworkService.loadBrands().also {
                        brandsDAO.insertAll(it)
                    }
                } catch (ex: IOException){
                    brandsDAO.getAll()
                }
                _screenState.value = ScreenState.DataLoaded(brands)
            } catch ( ex: Throwable) {
                _screenState.value = ScreenState.Error("Внимание, ошибка!!!")
            }
        }
    }
}