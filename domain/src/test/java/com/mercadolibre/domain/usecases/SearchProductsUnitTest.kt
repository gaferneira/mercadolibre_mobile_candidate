package com.mercadolibre.domain.usecases

import com.mercadolibre.domain.TestCoroutineRule
import com.mercadolibre.domain.base.Failure
import com.mercadolibre.domain.base.Result
import com.mercadolibre.domain.factory.ProductsFactory
import com.mercadolibre.domain.relaxedMockk
import com.mercadolibre.domain.repositories.ProductsRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class SearchProductsUnitTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @RelaxedMockK
    private lateinit var repository: ProductsRepository

    private lateinit var useCase: SearchProductsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `When service is called, it should return a list`() {

        repository = relaxedMockk {
            coEvery { searchByQuery(any()) } returns Result.Success(
                ProductsFactory.generateDummyProductList(
                    10
                )
            )
        }

        useCase = SearchProductsUseCase(repository)

        testCoroutineRule.runBlockingTest {
            val response = useCase.invoke("query")
            assert(response is Result.Success)
            assertFalse((response as Result.Success).data.isNullOrEmpty())
        }
    }

    @Test
    fun `When service fails, it should return an error`() {

        repository = relaxedMockk {
            coEvery { searchByQuery(any()) } returns Result.Error(Failure.UnknownException())
        }

        useCase = SearchProductsUseCase(repository)

        testCoroutineRule.runBlockingTest {
            val response = useCase.invoke("query")
            assert(response is Result.Error)
        }
    }
}
