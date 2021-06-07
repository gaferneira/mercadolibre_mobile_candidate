package com.mercadolibre.domain.usecases

import com.mercadolibre.domain.base.Failure
import com.mercadolibre.domain.base.Result
import com.mercadolibre.domain.factory.RecentSearchFactory
import com.mercadolibre.domain.repositories.RecentSearchRepository
import com.mercadolibre.domain.utils.TestCoroutineRule
import com.mercadolibre.domain.utils.relaxedMockk
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class GetRecentSearchUnitTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @RelaxedMockK
    private lateinit var repository: RecentSearchRepository

    private lateinit var useCase: GetRecentSearchUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `When repository is called, it should return a list`() {

        repository = relaxedMockk {
            coEvery { getRecentSearch() } returns RecentSearchFactory.generateDummyList(10).let {
                Result.Success(it)
            }
        }

        useCase = GetRecentSearchUseCase(repository)

        testCoroutineRule.runBlockingTest {
            val response = useCase.invoke()
            assert(response is Result.Success)
        }
    }

    @Test
    fun `When repository fails, it should return an error`() {

        repository = relaxedMockk {
            coEvery { getRecentSearch() } returns Result.Error(Failure.DataNotFound())
        }

        useCase = GetRecentSearchUseCase(repository)

        testCoroutineRule.runBlockingTest {
            val response = useCase.invoke()
            assert(response is Result.Error)
        }
    }
}
