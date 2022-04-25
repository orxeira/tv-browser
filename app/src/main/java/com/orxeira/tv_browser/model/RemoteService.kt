package com.orxeira.tv_browser.model

import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {

    @GET("tv/top_rated")
    suspend fun listTopRatedTvShows(
        @Query("api_key") apiKey: String
    ): RemoteResult

}