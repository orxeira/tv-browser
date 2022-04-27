package com.orxeira.usecases

import com.orxeira.data.TvShowRepository
import com.orxeira.domain.Error

class RequestPopularTvShowsUseCase(private val repository: TvShowRepository) {

    suspend operator fun invoke(): Error? {
        return repository.requestPopularTvShows()
    }
}