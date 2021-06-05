package com.mercadolibre.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mercadolibre.domain.usecases.SearchProductsUseCase
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject
import com.mercadolibre.domain.base.Result
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var searchProducts: SearchProductsUseCase

    private val scope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        scope.launch {
            test()
        }
    }

    suspend fun test() {
        when (val result = searchProducts("motorola")){
            is Result.Success -> {
                Timber.d("Success %s", result.data.size)
            }
            is Result.Error -> {
                Timber.d("Error: %s", result.failure.toString())
            }
        }
    }
}
