package com.orxeira.tv_browser.framework.server

import arrow.core.Either
import com.orxeira.data.datasource.TvShowRemoteDataSource
import com.orxeira.domain.Error
import com.orxeira.domain.TvShow
import com.orxeira.tv_browser.framework.tryCall

class TvShowServerDataSource(private val apiKey: String) : TvShowRemoteDataSource {

    override suspend fun findTopRatedTvShows(language: String): Either<Error, List<TvShow>> =
        tryCall {
            RemoteConnection.service
                .listTopRatedTvShows(apiKey, language)
                .results
                .toDomainModel()
        }
}

private fun List<RemoteTvShow>.toDomainModel(): List<TvShow> =
    map { it.toDomainModel() }

private fun RemoteTvShow.toDomainModel(): TvShow =
    TvShow(
        id,
        name,
        overview,
        "https://image.tmdb.org/t/p/w185/$posterPath",
        backdropPath?.let { "https://image.tmdb.org/t/p/w780/$it" } ?: "",
        voteAverage,
        firstAirDate,
    )

