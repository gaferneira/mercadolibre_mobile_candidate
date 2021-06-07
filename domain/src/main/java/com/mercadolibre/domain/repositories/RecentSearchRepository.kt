package com.mercadolibre.domain.repositories

import com.mercadolibre.domain.base.Result
import com.mercadolibre.domain.entities.RecentSearch

interface RecentSearchRepository {
    suspend fun addRecentSearch(query: String): Result<RecentSearch>
    suspend fun getRecentSearch(): Result<List<RecentSearch>>
}
