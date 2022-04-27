package com.orxeira.tv_browser.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.orxeira.domain.Error
import com.orxeira.domain.TvShow
import com.orxeira.tv_browser.data.toError
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    getPopularTvShowsUseCase: com.orxeira.usecases.GetPopularTvShowsUseCase,
    private val requestPopularTvShowsUseCase: com.orxeira.usecases.RequestPopularTvShowsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getPopularTvShowsUseCase()
                .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
                .collect { tvShows -> _state.update { UiState(tvShows = tvShows) } }
        }
    }

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)
            val error = requestPopularTvShowsUseCase()
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
class MainViewModelFactory(
    private val getPopularTvShowsUseCase: com.orxeira.usecases.GetPopularTvShowsUseCase,
    private val requestPopularTvShowsUseCase: com.orxeira.usecases.RequestPopularTvShowsUseCase
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(getPopularTvShowsUseCase, requestPopularTvShowsUseCase) as T
    }
}
