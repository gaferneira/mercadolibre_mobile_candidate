package com.mercadolibre.domain.usecases

import com.mercadolibre.domain.base.Failure
import com.mercadolibre.domain.base.NoParamsUseCase
import com.mercadolibre.domain.base.Result
import com.mercadolibre.domain.entities.RecentSearch
import com.mercadolibre.domain.repositories.RecentSearchRepository

class GetRecentSearchUseCase(private val repository: RecentSearchRepository) : NoParamsUseCase<List<RecentSearch>> {
    override suspend fun invoke(): Result<List<RecentSearch>> {
        return try {
            repository.getRecentSearch()
        } catch (exception : Exception) {
            Result.Error(Failure.analyzeException(exception))
        }
    }
}

