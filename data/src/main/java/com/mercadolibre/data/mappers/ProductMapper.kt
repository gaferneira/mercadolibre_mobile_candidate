package com.mercadolibre.data.mappers

import com.mercadolibre.data.entities.EshopEntity
import com.mercadolibre.data.entities.ProductEntity
import com.mercadolibre.data.entities.SellerEntity
import com.mercadolibre.domain.entities.Product


class ProductMapper : Mapper<ProductEntity, Product> {

    override fun mapFromEntity(type: ProductEntity): Product {
        return with(type) {
            Product(
                acceptsMercadoPago,
                availableQuantity,
                buyingMode,
                catalogListing,
                catalogProductId,
                categoryId,
                condition,
                currencyId,
                domainId,
                id,
                listingTypeId,
                orderBackend,
                permalink,
                price,
                seller?.eshop?.nickName.orEmpty(),
                siteId,
                soldQuantity,
                stopTime,
                thumbnail,
                thumbnailId,
                title,
                useThumbnailId
            )
        }
    }

    override fun mapToEntity(type: Product): ProductEntity {
        return with(type) {
            ProductEntity(
                acceptsMercadoPago,
                availableQuantity,
                buyingMode,
                catalogListing,
                catalogProductId,
                categoryId,
                condition,
                currencyId,
                domainId,
                id,
                listingTypeId,
                orderBackend,
                permalink,
                price,
                SellerEntity(EshopEntity(seller)),
                siteId,
                soldQuantity,
                stopTime,
                thumbnail,
                thumbnailId,
                title,
                useThumbnailId
            )
        }
    }
}
