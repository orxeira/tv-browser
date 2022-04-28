package com.orxeira.tv_browser.data.server

import arrow.core.Either
import com.orxeira.data.datasource.TvShowRemoteDataSource
import com.orxeira.domain.Error
import com.orxeira.domain.TvShow
import com.orxeira.tv_browser.data.tryCall

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
        "https://image.tmdb.org/t/p/w780/${backdropPath ?: posterPath}",
        voteAverage,
        firstAirDate,
    )