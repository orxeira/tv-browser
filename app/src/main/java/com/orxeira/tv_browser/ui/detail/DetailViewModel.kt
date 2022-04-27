package com.orxeira.tv_browser.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.orxeira.tv_browser.model.Error
import com.orxeira.tv_browser.model.TvShowRepository
import com.orxeira.tv_browser.model.database.TvShow
import com.orxeira.tv_browser.model.toError
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailViewModel(
    tvShowId: Int,
    private val repository: TvShowRepository
) : ViewModel() {

    data class UiState(val tvShow: TvShow? = null, val error: Error? = null)

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            repository.findById(tvShowId)
                .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
                .collect { tvShow ->
                    _state.update { UiState(tvShow) }
                }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val tvShowId: Int, private val repository: TvShowRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(tvShowId, repository) as T
    }
}