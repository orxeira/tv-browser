package com.orxeira.tv_browser.model

import androidx.appcompat.app.AppCompatActivity
import com.orxeira.tv_browser.R

class TvShowRepository(activity: AppCompatActivity) {

    private val apiKey = activity.getString(R.string.api_key)

    suspend fun findTopRatedShows() =
        RemoteConnection.service
            .listTopRatedTvShows(
                apiKey
            )
}