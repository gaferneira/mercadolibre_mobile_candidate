package com.mercadolibre.mobile.factory

import com.mercadolibre.domain.entities.Product

object ProductsFactory {

    fun generateDummyProductList(size: Int): List<Product> {
        val movieList = mutableListOf<Product>()
        repeat(size) {
            movieList.add(generateProduct())
        }
        return movieList
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
