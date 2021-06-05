package com.mercadolibre.data.entities

import com.google.gson.annotations.SerializedName

data class SellerEntity(
    @SerializedName("eshop")
    val eshop: EshopEntity? = EshopEntity(),
)
