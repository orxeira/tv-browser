package com.orxeira.tv_browser.data

import com.orxeira.tv_browser.R
import com.orxeira.tv_browser.data.datasource.TvShowLocalDataSource
import com.orxeira.tv_browser.data.datasource.TvShowRemoteDataSource
import com.orxeira.tv_browser.App
import com.orxeira.tv_browser.domain.TvShow
import com.orxeira.tv_browser.framework.datasource.TvShowRoomDataSource
import com.orxeira.tv_browser.framework.datasource.TvShowServerDataSource
import kotlinx.coroutines.flow.Flow
import com.orxeira.tv_browser.data.TvShow as RemoteTvShow

/**
 * TvShow repository ensures a single data source is used internally making sure the app is fully
 * synchronized.
 */
class TvShowRepository(application: App) {

    private val regionRepository = RegionRepository(application)
    private val localDataSource: TvShowLocalDataSource = TvShowRoomDataSource(application.db.tvShowDao())
    private val remoteDataSource: TvShowRemoteDataSource = TvShowServerDataSource(application.getString(R.string.api_key))

    val popularTvShows = localDataSource.tvShows

    fun findById(id: Int): Flow<TvShow> = localDataSource.findById(id)

    suspend fun requestPopularTvShows(): Error? = tryCall {
        if (localDataSource.isEmpty()) {
            val tvShows = remoteDataSource.findTopRatedTvShows(regionRepository.findLastLanguage())
            localDataSource.save(tvShows.results.toLocalModel())
        }
    }
}

private fun List<RemoteTvShow>.toLocalModel(): List<TvShow> = this.map { it.toLocalModel() }

private fun RemoteTvShow.toLocalModel(): TvShow = TvShow(
    id,
    name,
    overview,
    posterPath ?: "",
    backdropPath ?: "",
    voteAverage,
    firstAirDate,
)