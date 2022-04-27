package com.orxeira.tv_browser.framework.server

import com.google.gson.annotations.SerializedName

data class RemoteResult(
    val page: Int,
    val results: List<RemoteTvShow>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)

data class RemoteTvShow(
    val id: Int,
    val name: String,
    val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("first_air_date") val firstAirDate: String,
)