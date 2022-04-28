package com.orxeira.usecases

import com.orxeira.data.TvShowRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class RequestPopularTvShowsUseCaseTest {
    @Test
    fun `Invoke calls movies repository`(): Unit = runBlocking {
        val moviesRepository = mock<TvShowRepository>()
        val requestPopularMoviesUseCase = RequestPopularTvShowsUseCase(moviesRepository)

        requestPopularMoviesUseCase()

        verify(moviesRepository).requestPopularTvShows()
    }
}