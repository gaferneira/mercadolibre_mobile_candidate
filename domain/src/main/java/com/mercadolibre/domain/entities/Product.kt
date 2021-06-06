package com.mercadolibre.domain.entities

import java.io.Serializable

data class Product(
    val acceptsMercadoPago: Boolean? = false,
    val availableQuantity: Int? = 0,
    val buyingMode: String? = "",
    val catalogListing: Boolean? = false,
    val catalogProductId: String? = "",
    val categoryId: String? = "",
    val condition: String? = "",
    val currencyId: String? = "",
    val domainId: String? = "",
    val id: String? = "",
    val listingTypeId: String? = "",
    val orderBackend: Int? = 0,
    val permalink: String? = "",
    val price: Float? = 0f,
    val seller: String = "",
    val siteId: String? = "",
    val soldQuantity: Int? = 0,
    val stopTime: String? = "",
    val thumbnail: String? = "",
    val thumbnailId: String? = "",
    val title: String? = "",
    val useThumbnailId: Boolean? = false
) : Serializable
