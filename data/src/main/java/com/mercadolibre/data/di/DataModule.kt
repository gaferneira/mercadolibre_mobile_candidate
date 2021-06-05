package com.mercadolibre.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mercadolibre.data.remote.ProductsService
import com.mercadolibre.data.repositories.ProductsRepositoryImpl
import com.mercadolibre.domain.repositories.ProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.mercadolibre.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideProductsService(retrofit: Retrofit): ProductsService =
        retrofit.create(ProductsService::class.java)

    @Singleton
    @Provides
    fun provideProductRepository(service: ProductsService): ProductsRepository =
        ProductsRepositoryImpl(service)
}
