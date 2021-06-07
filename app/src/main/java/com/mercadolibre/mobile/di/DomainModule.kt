package com.mercadolibre.mobile.di

import com.mercadolibre.domain.repositories.PreferencesRepository
import com.mercadolibre.domain.repositories.ProductsRepository
import com.mercadolibre.domain.repositories.RecentSearchRepository
import com.mercadolibre.domain.usecases.SearchProductsUseCase
import com.mercadolibre.domain.usecases.GetRecentSearchUseCase
import com.mercadolibre.domain.usecases.AddRecentSearchUseCase
import com.mercadolibre.domain.usecases.UpdateCountryUseCase
import com.mercadolibre.domain.usecases.GetCountryUseCase
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
    fun provideSearchProductUseCase(repository : ProductsRepository) : SearchProductsUseCase =
        SearchProductsUseCase(repository)

    @Singleton
    @Provides
    fun provideUpdateCountryUseCase(repository : PreferencesRepository) : UpdateCountryUseCase =
        UpdateCountryUseCase(repository)

    @Singleton
    @Provides
    fun provideGetCountryUseCase(repository : PreferencesRepository) : GetCountryUseCase =
        GetCountryUseCase(repository)

    @Singleton
    @Provides
    fun provideGetRecentSearchUseCase(repository : RecentSearchRepository) : GetRecentSearchUseCase =
        GetRecentSearchUseCase(repository)

    @Singleton
    @Provides
    fun provideAddRecentSearchUseCase(repository : RecentSearchRepository) : AddRecentSearchUseCase =
        AddRecentSearchUseCase(repository)

}
