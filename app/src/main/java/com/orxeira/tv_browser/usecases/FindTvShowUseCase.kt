package com.orxeira.tv_browser.usecases

import com.orxeira.tv_browser.data.TvShowRepository

class FindTvShowUseCase (private val repository: TvShowRepository) {

    operator fun invoke(id: Int) = repository.findById(id)
}