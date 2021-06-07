package com.mercadolibre.mobile.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.switchMap
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.mercadolibre.domain.base.Result
import com.mercadolibre.domain.usecases.SearchProductsUseCase
import com.mercadolibre.mobile.utils.view.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private var searchProducts: SearchProductsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val searchText = savedStateHandle.getLiveData("search", "")

    val products = searchText.switchMap { query ->
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            if (query.isEmpty()) {
                emit(Resource.None)
                return@liveData
            }
            emit(Resource.Loading)
            when (val response = searchProducts(query)) {
                is Result.Success -> {
                    emit(Resource.Success(response.data))
                }
                is Result.Error -> {
                    emit(Resource.Error(response.failure))
                }
            }
        }
    }

    fun search(query: String) {
        searchText.value = query
    }

}
