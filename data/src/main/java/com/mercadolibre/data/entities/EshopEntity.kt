package com.mercadolibre.data.entities

import com.google.gson.annotations.SerializedName

data class EshopEntity(
    @SerializedName("nick_name")
    val nickName: String? = ""
)
