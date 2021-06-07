package com.mercadolibre.domain.usecases

import com.mercadolibre.domain.base.Failure
import com.mercadolibre.domain.base.Result
import com.mercadolibre.domain.base.UseCase
import com.mercadolibre.domain.entities.Country
import com.mercadolibre.domain.repositories.PreferencesRepository

class UpdateCountryUseCase(private val repository: PreferencesRepository) : UseCase<Country, Country> {
    override suspend fun invoke(params: Country): Result<Country> {
        return try {
            repository.setCountry(params)
        } catch (exception : Exception) {
            Result.Error(Failure.analyzeException(exception))
        }
    }
}
