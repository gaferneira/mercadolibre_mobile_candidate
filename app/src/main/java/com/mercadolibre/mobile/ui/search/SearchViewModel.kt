package com.mercadolibre.mobile.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import com.mercadolibre.domain.base.Result
import com.mercadolibre.domain.entities.RecentSearch
import com.mercadolibre.domain.usecases.AddRecentSearchUseCase
import com.mercadolibre.domain.usecases.GetRecentSearchUseCase
import com.mercadolibre.mobile.utils.view.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getRecentSearchUseCase: GetRecentSearchUseCase,
    private val addRecentSearchUseCase: AddRecentSearchUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _recentSearchList: MutableLiveData<Resource<List<RecentSearch>>> =
        savedStateHandle.getLiveData("recentSearchList", Resource.Loading)
    val recentSearchList: LiveData<Resource<List<RecentSearch>>>
        get() = _recentSearchList

    init {
        viewModelScope.launch {
            val list = withContext(Dispatchers.IO) {
                when (val response = getRecentSearchUseCase()) {
                    is Result.Success -> response.data
                    is Result.Error -> listOf()
                }
            }
            _recentSearchList.value = Resource.Success(list)
        }
    }

    fun addRecentSearch(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            addRecentSearchUseCase(query)
        }
    }
}
