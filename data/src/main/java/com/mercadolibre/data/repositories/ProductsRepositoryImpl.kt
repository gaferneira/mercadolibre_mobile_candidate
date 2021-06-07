package com.mercadolibre.data.repositories

import com.mercadolibre.data.mappers.ProductMapper
import com.mercadolibre.data.remote.ProductsService
import com.mercadolibre.data.utils.toFailure
import com.mercadolibre.domain.base.Result
import com.mercadolibre.domain.entities.Country
import com.mercadolibre.domain.entities.Product
import com.mercadolibre.domain.repositories.PreferencesRepository
import com.mercadolibre.domain.repositories.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl
        @Inject constructor(
            private val service: ProductsService,
            private val preferencesRepository: PreferencesRepository,
        ) : ProductsRepository {

    override suspend fun searchByQuery(query: String): Result<List<Product>> {
        val params = hashMapOf("q" to query)
        val response = service.searchByQuery(getSite(), params)
        return if (response.isSuccessful) {
            val mapper = ProductMapper()
            Result.Success(response.body()?.results?.map { mapper.mapFromEntity(it) } ?: listOf())
        } else {
            Result.Error(response.toFailure())
        }
    }

    private suspend fun getSite(): String =
        preferencesRepository.getCountry()?.id ?: Country.ARGENTINA.id
}
