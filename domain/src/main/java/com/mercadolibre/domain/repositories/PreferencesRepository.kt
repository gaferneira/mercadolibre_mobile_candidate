package com.mercadolibre.domain.repositories

import com.mercadolibre.domain.base.Result
import com.mercadolibre.domain.entities.Country

interface PreferencesRepository {
    suspend fun getCountry(): Country?
    suspend fun setCountry(country: Country): Result<Country>
}
