package com.mercadolibre.domain.usecases

import com.mercadolibre.domain.base.Failure
import com.mercadolibre.domain.base.NoParamsUseCase
import com.mercadolibre.domain.base.Result
import com.mercadolibre.domain.entities.Country
import com.mercadolibre.domain.repositories.PreferencesRepository

class GetCountryUseCase(private val repository: PreferencesRepository) : NoParamsUseCase<Country> {
    override suspend fun invoke(): Result<Country> {
        return try {
            repository.getCountry()?.let {
                Result.Success(it)
            } ?: Result.Error(Failure.DataNotFound())

        } catch (exception : Exception) {
            Result.Error(Failure.analyzeException(exception))
        }
    }
}

