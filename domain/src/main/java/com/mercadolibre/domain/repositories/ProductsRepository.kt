package com.mercadolibre.domain.repositories

import com.mercadolibre.domain.base.Result
import com.mercadolibre.domain.entities.Product

interface ProductsRepository {
    suspend fun searchByQuery(query: String): Result<List<Product>>
}
