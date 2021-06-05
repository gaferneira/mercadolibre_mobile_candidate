package com.mercadolibre.data.repositories

import com.mercadolibre.data.mappers.ProductMapper
import com.mercadolibre.data.remote.ProductsService
import com.mercadolibre.data.utils.toFailure
import com.mercadolibre.domain.base.Result
import com.mercadolibre.domain.entities.Product
import com.mercadolibre.domain.repositories.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(private val service: ProductsService) :
    ProductsRepository {

    override suspend fun searchByQuery(query: String): Result<List<Product>> {
        val params = hashMapOf("q" to query)
        val response = service.searchByQuery(params)
        return if (response.isSuccessful) {
            val mapper = ProductMapper()
            Result.Success(response.body()?.results?.map { mapper.mapFromEntity(it) } ?: listOf())
        } else {
            Result.Error(response.toFailure())
        }
    }
}

