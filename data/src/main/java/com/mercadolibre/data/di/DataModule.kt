package com.mercadolibre.data.di

import com.mercadolibre.data.repositories.ProductsRepositoryImpl
import com.mercadolibre.domain.repositories.ProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideProductRepository() : ProductsRepository = ProductsRepositoryImpl()
}
