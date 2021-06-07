package com.mercadolibre.domain.usecases

import com.mercadolibre.domain.utils.TestCoroutineRule
import com.mercadolibre.domain.base.Result
import com.mercadolibre.domain.entities.Country
import com.mercadolibre.domain.utils.relaxedMockk
import com.mercadolibre.domain.repositories.PreferencesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class GetCountryUnitTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @RelaxedMockK
    private lateinit var repository: PreferencesRepository

    private lateinit var useCase: GetCountryUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `When repository is called, it should return a country`() {

        repository = relaxedMockk {
            coEvery { getCountry() } returns Country.COLOMBIA
        }

        useCase = GetCountryUseCase(repository)

        testCoroutineRule.runBlockingTest {
            val response = useCase.invoke()
            assert(response is Result.Success)
        }
    }

    @Test
    fun `When repository returns null, it should return an error`() {

        repository = relaxedMockk {
            coEvery { getCountry() } returns null
        }

        useCase = GetCountryUseCase(repository)

        testCoroutineRule.runBlockingTest {
            val response = useCase.invoke()
            assert(response is Result.Error)
        }
    }
}
