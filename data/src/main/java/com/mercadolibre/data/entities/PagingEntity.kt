package com.mercadolibre.data.entities

import com.google.gson.annotations.SerializedName

data class PagingEntity(
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("primary_results")
    val primaryResults: Int?,
    @SerializedName("total")
    val total: Int?
)
