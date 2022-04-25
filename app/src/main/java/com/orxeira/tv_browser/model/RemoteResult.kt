package com.orxeira.tv_browser.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class RemoteResult(
    val page: Int,
    val results: List<TvShow>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)

@Parcelize
data class TvShow(
    val id: Int,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("vote_average") val voteAverage: Double,
    val overview: String,
    @SerializedName("original_name") val originalName: String,

    @SerializedName("genre_ids") val genreIds: List<Int>,
    @SerializedName("original_language") val originalLanguage: String,
    val adult: Boolean,
    @SerializedName("original_title") val originalTitle: String,
    val popularity: Double,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("first_air_date") val firstAirDate: String,
    val name: String,
    val video: Boolean,
    @SerializedName("vote_count") val voteCount: Int
) : Parcelable