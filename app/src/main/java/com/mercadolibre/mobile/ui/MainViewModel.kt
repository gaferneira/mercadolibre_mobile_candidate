package com.mercadolibre.mobile.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mercadolibre.domain.base.Result
import com.mercadolibre.domain.entities.Country
import com.mercadolibre.domain.usecases.GetCountryUseCase
import com.mercadolibre.domain.usecases.UpdateCountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCountryUseCase: GetCountryUseCase,
    private val updateCountryUseCase: UpdateCountryUseCase,
    private val savedStateHandle : SavedStateHandle
) : ViewModel() {

    private val _country: MutableLiveData<Country> = savedStateHandle.getLiveData("country")
    val country: LiveData<Country>
        get() = _country

    init {
        viewModelScope.launch {
            when (val response = getCountryUseCase()) {
                is Result.Success -> {
                    _country.value = response.data
                } else -> {
                    _country.value = Country.NONE
                }
            }
        }
    }

    fun updateCountry(country: Country) {
        viewModelScope.launch {
            _country.value = country
            updateCountryUseCase(country)
        }
    }
}
