package com.mercadolibre.mobile.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mercadolibre.mobile.R
import com.mercadolibre.mobile.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MercadoLibre)
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)
    }
}
