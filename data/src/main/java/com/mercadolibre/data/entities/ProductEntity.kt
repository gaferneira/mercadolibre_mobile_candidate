package com.mercadolibre.data.entities

import com.google.gson.annotations.SerializedName

data class ProductEntity(
    @SerializedName("accepts_mercadopago")
    val acceptsMercadoPago: Boolean? = false,
    @SerializedName("available_quantity")
    val availableQuantity: Int? = 0,
    @SerializedName("buying_mode")
    val buyingMode: String? = "",
    @SerializedName("catalog_listing")
    val catalogListing: Boolean? = false,
    @SerializedName("catalog_product_id")
    val catalogProductId: String? = "",
    @SerializedName("category_id")
    val categoryId: String? = "",
    @SerializedName("condition")
    val condition: String? = "",
    @SerializedName("currency_id")
    val currencyId: String? = "",
    @SerializedName("domain_id")
    val domainId: String? = "",
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("listing_type_id")
    val listingTypeId: String? = "",
    @SerializedName("order_backend")
    val orderBackend: Int? = 0,
    @SerializedName("permalink")
    val permalink: String? = "",
    @SerializedName("price")
    val price: Float? = 0f,
    @SerializedName("seller")
    val seller: SellerEntity? = SellerEntity(),
    @SerializedName("site_id")
    val siteId: String? = "",
    @SerializedName("sold_quantity")
    val soldQuantity: Int? = 0,
    @SerializedName("stop_time")
    val stopTime: String? = "",
    @SerializedName("thumbnail")
    val thumbnail: String? = "",
    @SerializedName("thumbnail_id")
    val thumbnailId: String? = "",
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("use_thumbnail_id")
    val useThumbnailId: Boolean? = false
)
