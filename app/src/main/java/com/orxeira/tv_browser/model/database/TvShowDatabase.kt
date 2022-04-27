package com.orxeira.tv_browser.model.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TvShow::class], version = 1, exportSchema = false)
abstract class TvShowDatabase : RoomDatabase() {

    abstract fun tvShowDao(): TvShowDao
}