package com.orxeira.tv_browser.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.orxeira.tv_browser.model.TvShow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailViewModel(tvShow: TvShow) : ViewModel() {

    class UiState(val tvShow: TvShow)

    private val _state = MutableStateFlow(UiState(tvShow))
    val state: StateFlow<UiState> = _state.asStateFlow()
}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val tvShow: TvShow) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(tvShow) as T
    }
}