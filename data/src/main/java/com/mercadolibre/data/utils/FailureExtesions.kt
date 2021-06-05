package com.mercadolibre.data.utils

import com.mercadolibre.domain.base.Failure
import retrofit2.Response
import java.net.HttpURLConnection

fun <T> Response<T>.toFailure(): Failure {
    return when (this.code()) {
        HttpURLConnection.HTTP_INTERNAL_ERROR -> Failure.ServerError()
        HttpURLConnection.HTTP_CLIENT_TIMEOUT -> Failure.NetworkConnection()
        else -> Failure.UnknownException()
    }
}
