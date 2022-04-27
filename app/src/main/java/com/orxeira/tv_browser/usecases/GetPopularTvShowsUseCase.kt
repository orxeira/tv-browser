package com.orxeira.tv_browser.usecases

import com.orxeira.tv_browser.data.TvShowRepository

class GetPopularTvShowsUseCase(private val repository: TvShowRepository) {

    operator fun invoke() = repository.popularTvShows
}