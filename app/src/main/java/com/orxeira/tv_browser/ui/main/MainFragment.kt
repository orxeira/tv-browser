package com.orxeira.tv_browser.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.orxeira.tv_browser.R
import com.orxeira.tv_browser.databinding.FragmentMainBinding
import com.orxeira.tv_browser.model.TvShowRepository
import com.orxeira.tv_browser.ui.common.launchAndCollect

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(TvShowRepository(requireActivity().application))
    }

    private lateinit var mainState: MainState

    private val adapter = TvShowAdapter { mainState.onTvShowClicked(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainState = buildMainState()

        val binding = FragmentMainBinding.bind(view).apply {
            recycler.adapter = adapter
        }

        viewLifecycleOwner.launchAndCollect(viewModel.state) {
            binding.loading = it.loading
            binding.tvShows = it.tvShows
        }

        viewModel.onUiReady()

    }
}