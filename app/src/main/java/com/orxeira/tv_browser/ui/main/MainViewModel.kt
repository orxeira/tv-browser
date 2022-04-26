package com.orxeira.tv_browser.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.orxeira.tv_browser.model.TvShow
import com.orxeira.tv_browser.model.TvShowRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val tvShowRepository: TvShowRepository) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(tvShows = tvShowRepository.findTopRatedShows().results)
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val tvShows: List<TvShow>? = null,
    )
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val tvShowRepository: TvShowRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(tvShowRepository) as T
    }
}