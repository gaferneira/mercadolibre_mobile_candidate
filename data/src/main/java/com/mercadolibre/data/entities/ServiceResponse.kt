package com.mercadolibre.data.entities

import com.google.gson.annotations.SerializedName

data class ServiceResponse(
    @SerializedName("paging")
    val paging: PagingEntity?,
    @SerializedName("query")
    val query: String?,
    @SerializedName("results")
    val results: List<ProductEntity>?,
    @SerializedName("site_id")
    val siteId: String?
)
