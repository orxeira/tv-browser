package com.orxeira.tv_browser.usecases

import com.orxeira.tv_browser.data.Error
import com.orxeira.tv_browser.data.TvShowRepository

class RequestPopularTvShowsUseCase(private val repository: TvShowRepository) {

    suspend operator fun invoke(): Error? {
        return repository.requestPopularTvShows()
    }
}