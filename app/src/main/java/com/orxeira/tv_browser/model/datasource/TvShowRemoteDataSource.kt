package com.orxeira.tv_browser.model.datasource

import com.orxeira.tv_browser.model.RemoteConnection

class TvShowRemoteDataSource(private val apiKey: String) {

    suspend fun findTopRatedTvShows(language: String) =
        RemoteConnection.service.listTopRatedTvShows(apiKey, language)
}