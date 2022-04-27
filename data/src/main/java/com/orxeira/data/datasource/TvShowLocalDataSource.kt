package com.orxeira.data.datasource

import com.orxeira.domain.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowLocalDataSource {
    val tvShows: Flow<List<TvShow>>

    suspend fun isEmpty(): Boolean
    fun findById(id: Int): Flow<TvShow>
    suspend fun save(tvShows: List<TvShow>)
}