package com.mercadolibre.mobile.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import com.mercadolibre.domain.entities.RecentSearch
import com.mercadolibre.mobile.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _recentSearchList: MutableLiveData<Resource<List<RecentSearch>>> =
        savedStateHandle.getLiveData("recentSearchList", Resource.Loading)
    val recentSearchList: LiveData<Resource<List<RecentSearch>>>
        get() = _recentSearchList

    init {
        viewModelScope.launch {
             //TODO implement recent search
             val response = listOf(
                 RecentSearch("motorola", Date()),
                 RecentSearch("samsung", Date()),
                 RecentSearch("iphone", Date())
             )
            _recentSearchList.value = Resource.Success(response)
        }
    }
}
