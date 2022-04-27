package com.orxeira.usecases

import com.orxeira.data.TvShowRepository

class FindTvShowUseCase(private val repository: TvShowRepository) {

    operator fun invoke(id: Int) = repository.findById(id)
}