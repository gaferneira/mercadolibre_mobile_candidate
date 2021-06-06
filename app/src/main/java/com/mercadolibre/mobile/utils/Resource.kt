package com.mercadolibre.mobile.utils

import com.mercadolibre.domain.base.Failure

sealed class Resource<out T> {
    object Loading: Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val failure: Failure) : Resource<Nothing>()
}
