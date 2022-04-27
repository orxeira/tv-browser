package com.orxeira.tv_browser

import android.app.Application
import androidx.room.Room
import com.orxeira.tv_browser.model.database.TvShowDatabase

class App : Application() {

    lateinit var db: TvShowDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            this,
            TvShowDatabase::class.java,
            "tvShow-db"
        ).build()
    }
}