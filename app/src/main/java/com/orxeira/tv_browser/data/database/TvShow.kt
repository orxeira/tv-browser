package com.orxeira.tv_browser.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TvShow(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val voteAverage: Double,
    val firstAirDate: String,
)