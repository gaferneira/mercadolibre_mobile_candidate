package com.mercadolibre.domain.base

interface NoParamsUseCase<out Type> where Type : Any {
    suspend operator fun invoke(): Result<Type>
}
