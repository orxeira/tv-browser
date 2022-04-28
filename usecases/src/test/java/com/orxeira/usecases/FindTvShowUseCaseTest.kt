package com.orxeira.usecases

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class FindTvShowUseCaseTest {
    @Test
    fun `Invoke calls movies repository`(): Unit = runBlocking {
        val tvShow = flowOf(sampleTvShow.copy(id = 1))
        val findMovieUseCase = FindTvShowUseCase(mock() {
            on { findById(1) } doReturn (tvShow)
        })

        val result = findMovieUseCase(1)

        assertEquals(tvShow, result)
    }
}