package com.orxeira.tv_browser.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.orxeira.tv_browser.model.Error
import com.orxeira.tv_browser.model.TvShowRepository
import com.orxeira.tv_browser.model.database.TvShow
import com.orxeira.tv_browser.model.toError
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val tvShowRepository: TvShowRepository) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            tvShowRepository.popularTvShows
                .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
                .collect { tvShows -> _state.update { UiState(tvShows = tvShows) } }
        }
    }

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)
            val error = tvShowRepository.requestPopularTvShows()
            _state.update { _state.value.copy(loading = false, error = error) }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val tvShows: List<TvShow>? = null,
        val error: Error? = null
    )
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val tvShowRepository: TvShowRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(tvShowRepository) as T
    }
}
