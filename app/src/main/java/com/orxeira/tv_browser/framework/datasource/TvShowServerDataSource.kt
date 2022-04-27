package com.orxeira.tv_browser.framework.datasource

import com.orxeira.tv_browser.data.RemoteConnection
import com.orxeira.tv_browser.data.datasource.TvShowRemoteDataSource

class TvShowServerDataSource(private val apiKey: String) : TvShowRemoteDataSource {

    override suspend fun findTopRatedTvShows(language: String) =
        RemoteConnection.service.listTopRatedTvShows(apiKey, language)
}