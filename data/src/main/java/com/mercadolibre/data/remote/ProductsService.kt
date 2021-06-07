package com.mercadolibre.data.remote

import com.mercadolibre.data.entities.ServiceResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ProductsService {
    @GET("sites/{site}/search?")
    suspend fun searchByQuery(
        @Path("site") site: String,
        @QueryMap query: HashMap<String, String>
    ): Response<ServiceResponse>
}
