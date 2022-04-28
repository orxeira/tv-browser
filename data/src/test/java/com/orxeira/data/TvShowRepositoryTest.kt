package com.orxeira.data

import arrow.core.right
import com.orxeira.data.datasource.TvShowLocalDataSource
import com.orxeira.data.datasource.TvShowRemoteDataSource
import com.orxeira.testshared.sampleTvShow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class TvShowRepositoryTest {

    @Mock
    lateinit var localDataSource: TvShowLocalDataSource

    @Mock
    lateinit var remoteDataSource: TvShowRemoteDataSource

    @Mock
    lateinit var languageRepository: LanguageRepository

    private lateinit var tvShowRepository: TvShowRepository

    private val localTvShows = flowOf(listOf(sampleTvShow.copy(1)))

    @Before
    fun setup() {
        whenever(localDataSource.tvShows).thenReturn(localTvShows)
        tvShowRepository = TvShowRepository(languageRepository, localDataSource, remoteDataSource)
    }

    @Test
    fun `Popular TvShows are taken from local datasource if available`(): Unit = runBlocking {

        val result = tvShowRepository.popularTvShows

        assertEquals(localTvShows, result)
    }

    @Test
    fun `Popular TvShows are saved to local datasource when it's empty`(): Unit =
        runBlocking {
            val remoteTvShows = listOf(sampleTvShow.copy(2))
            whenever(localDataSource.isEmpty()).thenReturn(true)
            whenever(languageRepository.findLastLanguage()).thenReturn(LanguageRepository.DEFAULT_LANGUAGE)
            whenever(remoteDataSource.findTopRatedTvShows(any())).thenReturn(remoteTvShows.right())

            tvShowRepository.requestPopularTvShows()

            verify(localDataSource).save(remoteTvShows)
        }

    @Test
    fun `Finding a TvShow by id is done in local data source`() {
        val tvShow = flowOf(sampleTvShow.copy(id = 5))
        whenever(localDataSource.findById(5)).thenReturn(tvShow)

        val result = tvShowRepository.findById(5)

        assertEquals(tvShow, result)
    }

}
