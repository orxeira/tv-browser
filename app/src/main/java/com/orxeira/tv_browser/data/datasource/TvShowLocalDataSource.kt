package com.orxeira.tv_browser.data.datasource

import com.orxeira.tv_browser.domain.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowLocalDataSource {
    val tvShows: Flow<List<TvShow>>

    suspend fun isEmpty(): Boolean
    fun findById(id: Int): Flow<TvShow>
    suspend fun save(tvShows: List<TvShow>)
}