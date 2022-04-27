package com.orxeira.data.datasource

import arrow.core.Either
import com.orxeira.domain.Error
import com.orxeira.domain.TvShow

interface TvShowRemoteDataSource {
    suspend fun findTopRatedTvShows(language: String): Either<Error, List<TvShow>>
}