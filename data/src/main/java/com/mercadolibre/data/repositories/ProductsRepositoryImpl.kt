package com.mercadolibre.data.repositories

import com.mercadolibre.domain.repositories.ProductsRepository

class ProductsRepositoryImpl : ProductsRepository {
    override suspend fun searchByQuery(query: String): List<String> {
        return listOf("a", "b", "c")
    }
}
