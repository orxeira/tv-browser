package com.orxeira.tv_browser.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.orxeira.domain.Error
import com.orxeira.domain.TvShow
import com.orxeira.tv_browser.data.toError
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailViewModel(
    tvShowId: Int,
    findTvShowUseCase: com.orxeira.usecases.FindTvShowUseCase
) : ViewModel() {

    data class UiState(val tvShow: TvShow? = null, val error: Error? = null)

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            findTvShowUseCase(tvShowId)
                .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
                .collect { tvShow ->
                    _state.update { UiState(tvShow) }
                }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(
    private val tvShowId: Int,
    private val findMovieUseCase: com.orxeira.usecases.FindTvShowUseCase
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(tvShowId, findMovieUseCase) as T
    }
}