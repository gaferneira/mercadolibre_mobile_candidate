package com.mercadolibre.data.repositories

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.mercadolibre.domain.base.Result
import com.mercadolibre.domain.entities.Country
import com.mercadolibre.domain.repositories.PreferencesRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore("profile")

class PreferencesRepositoryImpl @Inject constructor(appContext: Context) :
    PreferencesRepository {

    private val dataStore = appContext.dataStore

    override suspend fun getCountry(): Country? {
        return dataStore.data.firstOrNull()?.get(PREFERENCES_KEY_COUNTRY)?.let {
            Country.valueOf(it)
        }
    }

    override suspend fun setCountry(country: Country): Result<Country> {
        dataStore.edit { profile ->
            profile[PREFERENCES_KEY_COUNTRY] = country.name
        }
        return Result.Success(country)
    }

    companion object {
        val PREFERENCES_KEY_COUNTRY = stringPreferencesKey("country")
    }
}

