package com.orxeira.usecases

import com.orxeira.data.TvShowRepository

class GetPopularTvShowsUseCase(private val repository: TvShowRepository) {

    operator fun invoke() = repository.popularTvShows
}