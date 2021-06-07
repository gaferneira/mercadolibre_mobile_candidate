package com.mercadolibre.mobile.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asFlow
import com.mercadolibre.domain.base.Failure
import com.mercadolibre.domain.base.Result
import com.mercadolibre.domain.usecases.SearchProductsUseCase
import com.mercadolibre.mobile.factory.ProductsFactory
import com.mercadolibre.mobile.ui.home.HomeViewModel
import com.mercadolibre.mobile.utils.*
import com.mercadolibre.mobile.utils.view.Resource
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.Assert.fail
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @RelaxedMockK
    private lateinit var searchProducts: SearchProductsUseCase

    private lateinit var homeViewModel: HomeViewModel

    private val savedStateHandle = SavedStateHandle().apply {
        set("search", "")
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `when user searches, it should return a list of products`() =
        testCoroutineRule.runBlockingTest {

            //Given
            searchProducts = relaxedMockk {
                coEvery { this@relaxedMockk(any()) } returns Result.Success(
                    ProductsFactory.generateDummyProductList(10)
                )
            }
            homeViewModel = HomeViewModel(searchProducts, savedStateHandle)

            val job = launch {
                val flow = homeViewModel.products.asFlow()
                flow.collect { state ->
                    when (state) {
                        is Resource.Success -> {
                            //success
                            assert(state.data.isNotEmpty())
                            this.cancel()
                        }
                        is Resource.Error -> {
                            fail("It shouldn't fail")
                        }
                    }
                }
            }

            //when
            val query = "query"
            homeViewModel.search(query)

            //Should
            Assert.assertEquals(homeViewModel.searchText.getOrAwaitValue(), query)

            runBlocking {
                withTimeout(TIME_OUT) {
                    job.join()
                }
            }
        }

    @Test
    fun `when service fails, it should return an error state`() = testCoroutineRule.runBlockingTest {

        //Given
        searchProducts = relaxedMockk {
            coEvery { this@relaxedMockk(any()) } returns Result.Error(Failure.UnknownException())
        }
        homeViewModel = HomeViewModel(searchProducts, savedStateHandle)

        val job = launch {
            val flow = homeViewModel.products.asFlow()
            flow.collect { state ->
                when (state) {
                    is Resource.Success -> {
                        fail("It shouldn't be success")
                    }
                    is Resource.Error -> {
                        this.cancel()
                    }
                }
            }
        }

        //when
        val query = "query"
        homeViewModel.search(query)

        //Should
        Assert.assertEquals(homeViewModel.searchText.getOrAwaitValue(), query)

        runBlocking {
            withTimeout(TIME_OUT) {
                job.join()
            }
        }
    }

    companion object {
        const val TIME_OUT = 10_000L
    }

}
