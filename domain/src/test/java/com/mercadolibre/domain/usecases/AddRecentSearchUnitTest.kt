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
class AddRecentSearchUnitTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @RelaxedMockK
    private lateinit var repository: RecentSearchRepository

    private lateinit var useCase: AddRecentSearchUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `When repository is called, it should return success`() {

        repository = relaxedMockk {
            coEvery { addRecentSearch(any()) } returns Result.Success(RecentSearchFactory.generateRecentSearch())
        }

        useCase = AddRecentSearchUseCase(repository)

        testCoroutineRule.runBlockingTest {
            val response = useCase.invoke("query")
            assert(response is Result.Success)
        }
    }

    @Test
    fun `When repository fails, it should return an error`() {

        repository = relaxedMockk {
            coEvery { addRecentSearch(any()) } returns Result.Error(Failure.UnknownException())
        }

        useCase = AddRecentSearchUseCase(repository)

        testCoroutineRule.runBlockingTest {
            val response = useCase.invoke("query")
            assert(response is Result.Error)
        }
    }
}
