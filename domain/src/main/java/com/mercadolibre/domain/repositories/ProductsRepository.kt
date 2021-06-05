package com.mercadolibre.domain.repositories

interface ProductsRepository {

    suspend fun searchByQuery(query: String): List<String>

}
