package com.mercadolibre.mobile.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mercadolibre.domain.entities.Country
import com.mercadolibre.mobile.R
import com.mercadolibre.mobile.databinding.ActivityMainBinding
import com.mercadolibre.mobile.utils.LocationUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MercadoLibre)
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)
        initObservers()
    }

    private fun initObservers() {
        viewModel.country.observe(this, {
            if (it == Country.NONE) {
                setupPermissions()
            }
        })
    }

    private fun setupPermissions() {

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val permission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                AlertDialog.Builder(this)
                    .setMessage(R.string.message_location_permission)
                    .setPositiveButton(android.R.string.ok) { _, _ ->
                        makeLocationRequest()
                    }.create()
                    .show()
            } else {
                makeLocationRequest()
            }
        } else {
            getLastLocation()
        }
    }

    private fun makeLocationRequest() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
            LOCATION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                             permissions: Array<String>, grantResults: IntArray) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                    getLastLocation()
                } else {
                    updateCountry(LocationUtil.getCountry(this))
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                updateCountry(LocationUtil.getCountry(this, location))
            }
    }

    private fun updateCountry(countryCode : String?) {
        val country = Country.findByCode(countryCode) ?: Country.ARGENTINA //Default
        viewModel.updateCountry(country)
    }

    companion object {
        private const val LOCATION_REQUEST_CODE: Int = 23424
    }
}
