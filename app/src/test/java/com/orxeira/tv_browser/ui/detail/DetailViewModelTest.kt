package com.orxeira.tv_browser.ui.detail

import app.cash.turbine.test
import com.orxeira.testshared.sampleTvShow
import com.orxeira.tv_browser.CoroutinesTestRule
import com.orxeira.tv_browser.ui.detail.DetailViewModel.*
import com.orxeira.usecases.FindTvShowUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var findtvShowUseCase: FindTvShowUseCase

    private lateinit var vm: DetailViewModel

    private val tvShow = sampleTvShow.copy(id = 2)

    @Before
    fun setup() {
        whenever(findtvShowUseCase(2)).thenReturn(flowOf(tvShow))
        vm = DetailViewModel(2, findtvShowUseCase)
    }

    @Test
    fun `UI is updated with the movie on start`() = runTest {
        vm.state.test {
            assertEquals(UiState(), awaitItem())
            assertEquals(UiState(tvShow = tvShow), awaitItem())
            cancel()
        }
    }
}