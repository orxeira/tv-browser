package com.orxeira.tv_browser

import android.app.Application
import androidx.room.Room
import com.orxeira.tv_browser.data.database.TvShowDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initDI()
    }
}