package com.orxeira.tv_browser.data.database

import com.orxeira.data.datasource.TvShowLocalDataSource
import com.orxeira.domain.TvShow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.orxeira.tv_browser.data.database.TvShow as DbTvShow

class TvShowRoomDataSource(private val tvShowDao: TvShowDao) : TvShowLocalDataSource {

    override val tvShows: Flow<List<TvShow>> = tvShowDao.getAll().map { it.toDomainModel() }

    override suspend fun isEmpty(): Boolean = tvShowDao.tvShowCount() == 0

    override fun findById(id: Int): Flow<TvShow> = tvShowDao.findById(id).map { it.toDomainModel() }

    override suspend fun save(tvShows: List<TvShow>) {
        tvShowDao.insertTvShows(tvShows.fromDomainModel())
    }
}

private fun List<DbTvShow>.toDomainModel(): List<TvShow> = map { it.toDomainModel() }

private fun DbTvShow.toDomainModel(): TvShow =
    TvShow(
        id,
        name,
        overview,
        posterPath,
        backdropPath,
        voteAverage,
        firstAirDate,
    )

private fun List<TvShow>.fromDomainModel(): List<DbTvShow> =
    map { it.fromDomainModel() }

private fun TvShow.fromDomainModel(): DbTvShow =
    DbTvShow(
        id,
        name,
        overview,
        posterPath,
        backdropPath,
        voteAverage,
        firstAirDate,
    )