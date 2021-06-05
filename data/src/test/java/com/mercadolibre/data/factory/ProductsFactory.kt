package com.mercadolibre.data.factory

import com.mercadolibre.data.entities.EshopEntity
import com.mercadolibre.data.entities.ProductEntity
import com.mercadolibre.data.entities.SellerEntity
import com.mercadolibre.domain.entities.Product

object ProductsFactory {

    fun generateDummyProductsEntities(size: Int): List<ProductEntity> {
        val mutableProductDtoList = mutableListOf<ProductEntity>()
        repeat(size) {
            mutableProductDtoList.add(generateProductEntity())
        }

        return mutableProductDtoList
    }

    fun generateDummyProductList(size: Int): List<Product> {
        val movieList = mutableListOf<Product>()
        repeat(size) {
            movieList.add(generateProduct())
        }
        return movieList
    }

    fun generateProductEntity(): ProductEntity {
        return ProductEntity(
            acceptsMercadoPago = DataFactory.getRandomBoolean(),
            availableQuantity = DataFactory.getRandomInt(),
            buyingMode = DataFactory.getRandomString(),
            catalogListing = DataFactory.getRandomBoolean(),
            catalogProductId = DataFactory.getRandomString(),
            categoryId = DataFactory.getRandomString(),
            condition = DataFactory.getRandomString(),
            currencyId = DataFactory.getRandomString(),
            domainId = DataFactory.getRandomString(),
            id = DataFactory.getRandomString(),
            listingTypeId = DataFactory.getRandomString(),
            orderBackend = DataFactory.getRandomInt(),
            permalink = DataFactory.getRandomString(),
            price = DataFactory.getRandomFloat(),
            seller = SellerEntity(EshopEntity(DataFactory.getRandomString())),
            siteId = DataFactory.getRandomString(),
            soldQuantity = DataFactory.getRandomInt(),
            stopTime = DataFactory.getRandomString(),
            thumbnail = DataFactory.getRandomString(),
            thumbnailId = DataFactory.getRandomString(),
            title = DataFactory.getRandomString(),
            useThumbnailId = DataFactory.getRandomBoolean(),
        )
    }

    fun generateProduct(): Product {
        return Product(
            acceptsMercadoPago = DataFactory.getRandomBoolean(),
            availableQuantity = DataFactory.getRandomInt(),
            buyingMode = DataFactory.getRandomString(),
            catalogListing = DataFactory.getRandomBoolean(),
            catalogProductId = DataFactory.getRandomString(),
            categoryId = DataFactory.getRandomString(),
            condition = DataFactory.getRandomString(),
            currencyId = DataFactory.getRandomString(),
            domainId = DataFactory.getRandomString(),
            id = DataFactory.getRandomString(),
            listingTypeId = DataFactory.getRandomString(),
            orderBackend = DataFactory.getRandomInt(),
            permalink = DataFactory.getRandomString(),
            price = DataFactory.getRandomFloat(),
            seller = DataFactory.getRandomString(),
            siteId = DataFactory.getRandomString(),
            soldQuantity = DataFactory.getRandomInt(),
            stopTime = DataFactory.getRandomString(),
            thumbnail = DataFactory.getRandomString(),
            thumbnailId = DataFactory.getRandomString(),
            title = DataFactory.getRandomString(),
            useThumbnailId = DataFactory.getRandomBoolean(),
        )
    }
}
