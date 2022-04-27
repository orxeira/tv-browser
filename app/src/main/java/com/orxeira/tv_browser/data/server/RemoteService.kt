package com.orxeira.tv_browser.data.server

import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {

    @GET("tv/top_rated")
    suspend fun listTopRatedTvShows(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): RemoteResult

}