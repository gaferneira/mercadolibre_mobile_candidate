package com.mercadolibre.domain.usecases

import com.mercadolibre.domain.base.Failure
import com.mercadolibre.domain.base.Result
import com.mercadolibre.domain.base.UseCase
import com.mercadolibre.domain.entities.RecentSearch
import com.mercadolibre.domain.repositories.RecentSearchRepository

class AddRecentSearchUseCase(private val repository: RecentSearchRepository) :
    UseCase<RecentSearch, String> {
    override suspend fun invoke(params: String): Result<RecentSearch> {
        return try {
            repository.addRecentSearch(params)
        } catch (exception : Exception) {
            Result.Error(Failure.analyzeException(exception))
        }
    }
}

