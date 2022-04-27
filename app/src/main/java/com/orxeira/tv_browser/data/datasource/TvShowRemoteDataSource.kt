package com.orxeira.tv_browser.data.datasource

import com.orxeira.tv_browser.data.RemoteResult

interface TvShowRemoteDataSource {
    suspend fun findTopRatedTvShows(language: String): RemoteResult
}