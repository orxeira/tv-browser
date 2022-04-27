package com.orxeira.tv_browser.model.datasource

import com.orxeira.tv_browser.model.database.TvShow
import com.orxeira.tv_browser.model.database.TvShowDao
import kotlinx.coroutines.flow.Flow

class TvShowLocalDataSource(private val tvShowDao: TvShowDao) {
    val tvShows: Flow<List<TvShow>> = tvShowDao.getAll()

    suspend fun isEmpty(): Boolean = tvShowDao.tvShowCount() == 0

    fun findById(id: Int): Flow<TvShow> = tvShowDao.findById(id)

    suspend fun save(tvShows: List<TvShow>) {
        tvShowDao.insertTvShows(tvShows = tvShows)
    }
}