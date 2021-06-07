package com.mercadolibre.data.factory

import com.mercadolibre.data.entities.RecentSearchEntity
import com.mercadolibre.domain.entities.RecentSearch
import java.util.*

object RecentSearchFactory {

    fun generateDummyEntities(size: Int): List<RecentSearchEntity> {
        val mutableProductDtoList = mutableListOf<RecentSearchEntity>()
        repeat(size) {
            mutableProductDtoList.add(generateEntity())
        }

        return mutableProductDtoList
    }

    fun generateDummyList(size: Int): List<RecentSearch> {
        val movieList = mutableListOf<RecentSearch>()
        repeat(size) {
            movieList.add(generateRecentSearch())
        }
        return movieList
    }

    fun generateEntity(): RecentSearchEntity {
        return RecentSearchEntity(
            query = DataFactory.getRandomString(),
            lastSearchDate = DataFactory.getRandomLong(),
        )
    }

    fun generateRecentSearch(): RecentSearch {
        return RecentSearch(
            query = DataFactory.getRandomString(),
            lastSearchDate = Date()
        )
    }
}
