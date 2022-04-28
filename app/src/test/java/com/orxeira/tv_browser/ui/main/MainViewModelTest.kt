package com.orxeira.tv_browser.ui.main

import app.cash.turbine.test
import com.orxeira.testshared.sampleTvShow
import com.orxeira.tv_browser.CoroutinesTestRule
import com.orxeira.tv_browser.ui.main.MainViewModel.UiState
import com.orxeira.usecases.GetPopularTvShowsUseCase
import com.orxeira.usecases.RequestPopularTvShowsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    //The rule applies only on the getter.
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var getPopularTvShowsUseCase: GetPopularTvShowsUseCase

    @Mock
    lateinit var requestPopularTvShowsUseCase: RequestPopularTvShowsUseCase

    private lateinit var vm: MainViewModel

    private var tvShows = listOf(sampleTvShow.copy(1))


    @Before
    fun setup() {
        whenever(getPopularTvShowsUseCase()).thenReturn(flowOf(tvShows))
        vm = MainViewModel(getPopularTvShowsUseCase, requestPopularTvShowsUseCase)
    }


    @Test
    fun `State is updated with current cached content immediately`() = runTest {
        vm.state.test {
            assertEquals(UiState(), awaitItem())
            assertEquals(UiState(tvShows = tvShows), awaitItem())
            cancel()
        }
    }

    @Test
    fun `Progress is show when screen starts and hidden when finished obtaining movies`() =
        runTest {
            vm.onUiReady()

            vm.state.test {
                assertEquals(UiState(), awaitItem())
                assertEquals(UiState(tvShows = tvShows), awaitItem())
                assertEquals(UiState(tvShows = tvShows, loading = true), awaitItem())
                assertEquals(UiState(tvShows = tvShows, loading = false), awaitItem())
                cancel()
            }
        }

    @Test
    fun `Popular tvShows are requested when UI screen starts`() = runTest {
        vm.onUiReady()
        runCurrent()

        verify(requestPopularTvShowsUseCase).invoke()
    }
}