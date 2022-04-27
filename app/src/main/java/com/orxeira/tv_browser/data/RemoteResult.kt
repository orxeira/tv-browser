package com.orxeira.tv_browser.data

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
    val name: String,
    val overview: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("first_air_date") val firstAirDate: String,
) : Parcelable