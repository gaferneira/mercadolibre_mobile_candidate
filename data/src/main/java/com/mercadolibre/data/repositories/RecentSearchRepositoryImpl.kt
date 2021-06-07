package com.mercadolibre.data.repositories

import com.mercadolibre.data.entities.RecentSearchEntity
import com.mercadolibre.data.local.RecentSearchDao
import com.mercadolibre.data.mappers.RecentSearchMapper
import com.mercadolibre.domain.base.Result
import com.mercadolibre.domain.entities.RecentSearch
import com.mercadolibre.domain.repositories.RecentSearchRepository
import java.util.*
import javax.inject.Inject

class RecentSearchRepositoryImpl
        @Inject constructor(
            private val recentSearchDao : RecentSearchDao
        ) : RecentSearchRepository {

    private val mapper = RecentSearchMapper()

    override suspend fun addRecentSearch(query: String): Result<RecentSearch> {
        val item = RecentSearchEntity(query, Date().time)
        recentSearchDao.insert(item)
        return Result.Success(mapper.mapFromEntity(item))
    }

    override suspend fun getRecentSearch(): Result<List<RecentSearch>> {
        val response = recentSearchDao.getAll()
        return Result.Success(response.map { mapper.mapFromEntity(it) })
    }
}
