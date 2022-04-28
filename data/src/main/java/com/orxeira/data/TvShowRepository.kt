package com.orxeira.data

import com.orxeira.data.datasource.TvShowLocalDataSource
import com.orxeira.data.datasource.TvShowRemoteDataSource
import com.orxeira.domain.Error
import com.orxeira.domain.TvShow
import kotlinx.coroutines.flow.Flow

/**
 * TvShow repository ensures a single data source is used internally making sure the app is fully
 * synchronized.
 */
class TvShowRepository(
    private val languageRepository: LanguageRepository,
    private val localDataSource: TvShowLocalDataSource,
    private val remoteDataSource: TvShowRemoteDataSource
) {

    val popularTvShows = localDataSource.tvShows

    fun findById(id: Int): Flow<TvShow> = localDataSource.findById(id)

    suspend fun requestPopularTvShows(): Error? {
        if (localDataSource.isEmpty()) {
            val tvShows = remoteDataSource.findTopRatedTvShows(languageRepository.findLastLanguage())
            tvShows.fold(ifLeft = { return it }) {
                localDataSource.save(it)
            }
        }
        return null
    }
}