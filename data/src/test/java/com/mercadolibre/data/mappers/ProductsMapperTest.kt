package com.mercadolibre.data.mappers

import com.mercadolibre.data.entities.ProductEntity
import com.mercadolibre.data.factory.ProductsFactory
import com.mercadolibre.domain.entities.Product
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProductsMapperTest {

    private lateinit var movieMapper: ProductMapper

    @Before
    fun setUp() {
        movieMapper = ProductMapper()
    }

    @Test
    fun mapFromEntity() {
        // Arrange
        val productEntity = ProductsFactory.generateProductEntity()

        // Act
        val product = movieMapper.mapFromEntity(productEntity)

        // Assert
        assertProductDataEquality(productEntity, product)
    }

    @Test
    fun mapToEntity() {
        // Arrange
        val product = ProductsFactory.generateProduct()

        // Act
        val productEntity = movieMapper.mapToEntity(product)

        // Assert
        assertProductDataEquality(productEntity, product)
    }

    /**
     * Helper Methods
     */
    private fun assertProductDataEquality(productEntity: ProductEntity, product: Product) {
        assertEquals(productEntity.acceptsMercadoPago, product.acceptsMercadoPago)
        assertEquals(productEntity.availableQuantity, product.availableQuantity)
        assertEquals(productEntity.buyingMode, product.buyingMode)
        assertEquals(productEntity.catalogListing, product.catalogListing)
        assertEquals(productEntity.catalogProductId, product.catalogProductId)
        assertEquals(productEntity.categoryId, product.categoryId)
        assertEquals(productEntity.condition, product.condition)
        assertEquals(productEntity.currencyId, product.currencyId)
        assertEquals(productEntity.domainId, product.domainId)
        assertEquals(productEntity.id, product.id)
        assertEquals(productEntity.listingTypeId, product.listingTypeId)
        assertEquals(productEntity.orderBackend, product.orderBackend)
        assertEquals(productEntity.permalink, product.permalink)
        assertEquals(productEntity.price, product.price)
        assertEquals(productEntity.seller?.eshop?.nickName, product.seller)
        assertEquals(productEntity.siteId, product.siteId)
        assertEquals(productEntity.soldQuantity, product.soldQuantity)
        assertEquals(productEntity.stopTime, product.stopTime)
        assertEquals(productEntity.thumbnail, product.thumbnail)
        assertEquals(productEntity.thumbnailId, product.thumbnailId)
        assertEquals(productEntity.title, product.title)
        assertEquals(productEntity.useThumbnailId, product.useThumbnailId)
    }

}
