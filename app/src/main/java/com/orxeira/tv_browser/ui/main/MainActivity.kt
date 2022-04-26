package com.orxeira.tv_browser.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.orxeira.tv_browser.databinding.ActivityMainBinding
import com.orxeira.tv_browser.model.TvShow
import com.orxeira.tv_browser.model.TvShowRepository
import com.orxeira.tv_browser.ui.common.visible

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels { MainViewModelFactory(TvShowRepository(this)) }

    private val adapter = TvShowAdapter { viewModel.onTvShowClicked(it) }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recycler.adapter = adapter

        viewModel.state.observe(this, ::updateUI)
    }

    private fun updateUI(state: MainViewModel.UiState) {
        binding.progress.visible = state.loading
        state.tvShows?.let(adapter::submitList)
        state.navigateTo?.let(::navigateTo)
    }

    private fun navigateTo(tvShow: TvShow) {
    }
}