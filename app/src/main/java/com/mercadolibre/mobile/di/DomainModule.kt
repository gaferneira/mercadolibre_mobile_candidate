package com.mercadolibre.mobile.di

import com.mercadolibre.domain.repositories.ProductsRepository
import com.mercadolibre.domain.usecases.SearchProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun provideSearchProduct(repository : ProductsRepository) : SearchProductsUseCase =
        SearchProductsUseCase(repository)
}
