package com.orxeira.usecases

import com.orxeira.testshared.sampleTvShow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetPopularTvShowsUseCaseTest {
    @Test
    fun `Invoke calls movies repository`(): Unit = runBlocking {
        val tvShows = flowOf(listOf(sampleTvShow.copy(id = 1)))
        val getPopularMoviesUseCase = GetPopularTvShowsUseCase(mock {
            on { popularTvShows } doReturn tvShows
        })

        val result = getPopularMoviesUseCase()

        assertEquals(tvShows, result)
    }
}