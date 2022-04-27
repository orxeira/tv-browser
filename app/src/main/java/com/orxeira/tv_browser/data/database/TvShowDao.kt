package com.orxeira.tv_browser.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TvShowDao {

    @Query("SELECT * FROM TvShow ORDER BY voteAverage DESC")
    fun getAll(): Flow<List<TvShow>>

    @Query("Select * FROM TvShow WHERE id = :id")
    fun findById(id: Int): Flow<TvShow>

    @Query("SELECT COUNT(id) FROM TvShow")
    suspend fun tvShowCount(): Int

    @Insert(onConflict = IGNORE)
    suspend fun insertTvShows(tvShows: List<TvShow>)
}