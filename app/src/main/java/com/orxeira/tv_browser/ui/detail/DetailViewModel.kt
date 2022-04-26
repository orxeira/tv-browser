package com.orxeira.tv_browser.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.orxeira.tv_browser.model.TvShow

class DetailViewModel(tvShow: TvShow) : ViewModel() {

    class UiState(val tvShow: TvShow)

    private val _state = MutableLiveData(UiState(tvShow))
    val state: LiveData<UiState> get() = _state
}


@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val tvShow: TvShow) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(tvShow) as T
    }
}