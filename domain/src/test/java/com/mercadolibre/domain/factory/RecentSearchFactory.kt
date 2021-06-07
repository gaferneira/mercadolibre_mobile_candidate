package com.mercadolibre.domain.factory

import com.mercadolibre.domain.entities.RecentSearch
import java.util.*

object RecentSearchFactory {

    fun generateDummyList(size: Int): List<RecentSearch> {
        val movieList = mutableListOf<RecentSearch>()
        repeat(size) {
            movieList.add(generateRecentSearch())
        }
        return movieList
    }

    fun generateRecentSearch(): RecentSearch {
        return RecentSearch(
            query = DataFactory.getRandomString(),
            lastSearchDate = Date()
        )
    }
}
