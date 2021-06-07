package com.mercadolibre.data.mappers

import com.mercadolibre.data.entities.RecentSearchEntity
import com.mercadolibre.data.factory.RecentSearchFactory
import com.mercadolibre.domain.entities.RecentSearch
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RecentSearchMapperTest {

    private lateinit var mapper: RecentSearchMapper

    @Before
    fun setUp() {
        mapper = RecentSearchMapper()
    }

    @Test
    fun mapFromEntity() {
        // Arrange
        val productEntity = RecentSearchFactory.generateEntity()

        // Act
        val product = mapper.mapFromEntity(productEntity)

        // Assert
        assertDataEquality(productEntity, product)
    }

    @Test
    fun mapToEntity() {
        // Arrange
        val product = RecentSearchFactory.generateRecentSearch()

        // Act
        val productEntity = mapper.mapToEntity(product)

        // Assert
        assertDataEquality(productEntity, product)
    }

    /**
     * Helper Methods
     */
    private fun assertDataEquality(entity: RecentSearchEntity, recentSearch: RecentSearch) {
        assertEquals(entity.query, recentSearch.query)
        assertEquals(entity.lastSearchDate, recentSearch.lastSearchDate.time)

    }

}
