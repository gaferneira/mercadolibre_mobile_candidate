package com.mercadolibre.domain.usecases

import com.mercadolibre.domain.base.Failure
import com.mercadolibre.domain.base.Result
import com.mercadolibre.domain.base.UseCase
import com.mercadolibre.domain.entities.Product
import com.mercadolibre.domain.repositories.ProductsRepository

class SearchProductsUseCase(private val repository: ProductsRepository) : UseCase<List<Product>, String> {
    override suspend fun invoke(query: String): Result<List<Product>> {
        return try {
            repository.searchByQuery(query)
        } catch (exception : Exception) {
            Result.Error(Failure.analyzeException(exception))
        }
    }
}

