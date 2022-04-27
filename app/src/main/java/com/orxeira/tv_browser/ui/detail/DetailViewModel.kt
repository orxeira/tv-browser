package com.orxeira.tv_browser.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.orxeira.tv_browser.model.TvShowRepository
import com.orxeira.tv_browser.model.database.TvShow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel(
    tvShowId: Int,
    private val repository: TvShowRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.findById(tvShowId).collect {
                _state.value = UiState(it)
            }
        }
    }

    class UiState(val tvShow: TvShow? = null)

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()
}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val tvShowId: Int, private val repository: TvShowRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(tvShowId, repository) as T
    }
}