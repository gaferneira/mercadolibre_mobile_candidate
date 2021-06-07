package com.mercadolibre.mobile.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.mercadolibre.domain.base.Result
import com.mercadolibre.domain.entities.Country
import com.mercadolibre.domain.usecases.AddRecentSearchUseCase
import com.mercadolibre.domain.usecases.GetCountryUseCase
import com.mercadolibre.domain.usecases.GetRecentSearchUseCase
import com.mercadolibre.domain.usecases.UpdateCountryUseCase
import com.mercadolibre.mobile.factory.RecentSearchFactory
import com.mercadolibre.mobile.ui.search.SearchViewModel
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
class SearchViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @RelaxedMockK
    private lateinit var getRecentSearchUseCase: GetRecentSearchUseCase

    @RelaxedMockK
    private lateinit var addRecentSearchUseCase: AddRecentSearchUseCase

    private lateinit var viewModel: SearchViewModel

    private val savedStateHandle = SavedStateHandle().apply {
        set("recentSearchList", null)
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `when view model initializes, it should return a list`() =
        testCoroutineRule.runBlockingTest {

            //Given
            val list = RecentSearchFactory.generateDummyList(10)
            getRecentSearchUseCase = relaxedMockk {
                coEvery { this@relaxedMockk() } returns Result.Success(list)
            }
            addRecentSearchUseCase = relaxedMockk()

            //when
            viewModel = SearchViewModel(getRecentSearchUseCase, addRecentSearchUseCase, savedStateHandle)

            //Should
            assert(viewModel.recentSearchList.getOrAwaitValue() is Resource.Success)

        }
}
