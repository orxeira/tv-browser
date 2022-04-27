package com.orxeira.tv_browser.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TvShowDAO {

    @Query("SELECT * FROM TvShow")
    fun getAll(): Flow<List<TvShow>>

    @Query("Select * FROM TvShow WHERE id = :id")
    fun findById(id: Int): Flow<TvShow>

    @Query("SELECT COUNT(id) FROM TvShow")
    fun tvShowCount(): Int

    @Insert(onConflict = IGNORE)
    fun insertTvShows(tvShows: List<TvShow>)

    @Update
    fun updateTvShow(tvShow: TvShow)
}