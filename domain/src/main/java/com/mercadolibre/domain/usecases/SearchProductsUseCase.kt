package com.mercadolibre.domain.usecases

import com.mercadolibre.domain.base.Failure
import com.mercadolibre.domain.base.Result
import com.mercadolibre.domain.base.UseCase
import com.mercadolibre.domain.repositories.ProductsRepository

class SearchProductsUseCase(private val repository: ProductsRepository) : UseCase<List<String>, String> {
    override suspend fun invoke(query: String): Result<List<String>> {
        return try {
            Result.Success(repository.searchByQuery(query))
        } catch (exception : Exception) {
            Result.Error(Failure.analyzeException(exception))
        }
    }
}

