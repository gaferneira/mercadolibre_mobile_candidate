package com.mercadolibre.data.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mercadolibre.data.local.AppDatabase
import com.mercadolibre.data.local.RecentSearchDao
import com.mercadolibre.data.remote.ProductsService
import com.mercadolibre.data.repositories.PreferencesRepositoryImpl
import com.mercadolibre.data.repositories.ProductsRepositoryImpl
import com.mercadolibre.data.repositories.RecentSearchRepositoryImpl
import com.mercadolibre.domain.repositories.PreferencesRepository
import com.mercadolibre.domain.repositories.ProductsRepository
import com.mercadolibre.domain.repositories.RecentSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "MeliDB"
        ).build()
    }

    @Provides
    fun provideRecentSearchDao(appDatabase: AppDatabase): RecentSearchDao {
        return appDatabase.recentSearchDao()
    }

    @Singleton
    @Provides
    fun providePreferencesRepository(@ApplicationContext context: Context): PreferencesRepository = PreferencesRepositoryImpl(context)

    @Singleton
    @Provides
    fun provideProductRepository(service: ProductsService, preferencesRepository: PreferencesRepository): ProductsRepository =
        ProductsRepositoryImpl(service, preferencesRepository)

    @Singleton
    @Provides
    fun provideRecentSearchRepository(recentSearchDao: RecentSearchDao): RecentSearchRepository =
        RecentSearchRepositoryImpl(recentSearchDao)
}
