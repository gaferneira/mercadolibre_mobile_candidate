package com.mercadolibre.mobile.utils

import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.telephony.TelephonyManager
import timber.log.Timber
import java.io.IOException
import java.util.*

class LocationUtil private constructor(){

    companion object {
        fun getCountry(context: Context, location: Location? = null): String? {
            return try {
                if (location != null) {
                    val geocoder = Geocoder(context, Locale.getDefault())
                    val address = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                    address?.firstOrNull()?.countryCode?.let {
                        return@getCountry it
                    }
                }
                val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                tm.simCountryIso?.takeIf { it.isNotBlank() } ?: tm.networkCountryIso
            } catch (e: IOException) {
                Timber.e(e)
                null
            }
        }
    }
}
