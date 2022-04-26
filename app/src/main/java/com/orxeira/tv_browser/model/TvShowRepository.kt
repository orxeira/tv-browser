package com.orxeira.tv_browser.model

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.orxeira.tv_browser.R

class TvShowRepository(application: Application) {

    private val apiKey = application.getString(R.string.api_key)

    suspend fun findTopRatedShows() =
        RemoteConnection.service
            .listTopRatedTvShows(
                apiKey
            )
}