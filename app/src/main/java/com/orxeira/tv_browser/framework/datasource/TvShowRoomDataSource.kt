package com.orxeira.tv_browser.framework.datasource

import com.orxeira.tv_browser.data.datasource.TvShowLocalDataSource
import com.orxeira.tv_browser.domain.TvShow
import com.orxeira.tv_browser.framework.database.TvShowDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class TvShowRoomDataSource(private val tvShowDao: TvShowDao) : TvShowLocalDataSource {

    override val tvShows: Flow<List<TvShow>> = tvShowDao.getAll().map { it.toDomainModel() }

    override suspend fun isEmpty(): Boolean = tvShowDao.tvShowCount() == 0

    override fun findById(id: Int): Flow<TvShow> = tvShowDao.findById(id).map { it.toDomainModel() }

    override suspend fun save(tvShows: List<TvShow>) {
        tvShowDao.insertTvShows(tvShows.fromDomainModel())
    }
}

private fun List<com.orxeira.tv_browser.framework.database.TvShow>.toDomainModel(): List<TvShow> = map { it.toDomainModel() }

private fun com.orxeira.tv_browser.framework.database.TvShow.toDomainModel(): TvShow = TvShow(
    id,
    name,
    overview,
    posterPath,
    backdropPath,
    voteAverage,
    firstAirDate,
)

private fun List<TvShow>.fromDomainModel(): List<com.orxeira.tv_browser.framework.database.TvShow> = map { it.fromDomainModel() }

private fun TvShow.fromDomainModel(): com.orxeira.tv_browser.framework.database.TvShow =
    com.orxeira.tv_browser.framework.database.TvShow(
        id,
        name,
        overview,
        posterPath,
        backdropPath,
        voteAverage,
        firstAirDate,
    )