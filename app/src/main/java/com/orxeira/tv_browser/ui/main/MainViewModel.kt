package com.orxeira.tv_browser.ui.main

import androidx.lifecycle.*
import com.orxeira.tv_browser.model.TvShow
import com.orxeira.tv_browser.model.TvShowRepository
import kotlinx.coroutines.launch

class MainViewModel(private val tvShowRepository: TvShowRepository) : ViewModel() {

    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState>
        get() {
        if(_state.value?.tvShows == null){
            refresh()
        }
        return _state
    }

    private fun refresh(){
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(tvShows = tvShowRepository.findTopRatedShows().results)
        }
    }

    fun onTvShowClicked(movie: TvShow){
        _state.value = _state.value?.copy(navigateTo = movie)
    }

    data class UiState(
        val loading: Boolean = false,
        val tvShows: List<TvShow>? = null,
        val navigateTo: TvShow? = null
    )
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val tvShowRepository: TvShowRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(tvShowRepository) as T
    }
}