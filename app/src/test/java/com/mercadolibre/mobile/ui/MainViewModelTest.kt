package com.mercadolibre.mobile.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.mercadolibre.domain.base.Result
import com.mercadolibre.domain.entities.Country
import com.mercadolibre.domain.usecases.GetCountryUseCase
import com.mercadolibre.domain.usecases.UpdateCountryUseCase
import com.mercadolibre.mobile.utils.*
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @RelaxedMockK
    private lateinit var getCountry: GetCountryUseCase

    @RelaxedMockK
    private lateinit var updateCountry: UpdateCountryUseCase

    private lateinit var viewModel: MainViewModel

    private val savedStateHandle = SavedStateHandle().apply {
        set("country", null)
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `when view model initializes, it should return a country`() =
        testCoroutineRule.runBlockingTest {

            //Given
            getCountry = relaxedMockk {
                coEvery { this@relaxedMockk() } returns Result.Success(Country.COLOMBIA)
            }
            updateCountry = relaxedMockk()

            //when
            viewModel = MainViewModel(getCountry, updateCountry, savedStateHandle)

            //Should
            Assert.assertEquals(viewModel.country.getOrAwaitValue(), Country.COLOMBIA)

        }
}
