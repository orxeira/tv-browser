package com.orxeira.tv_browser.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.orxeira.data.RegionRepository
import com.orxeira.data.TvShowRepository
import com.orxeira.tv_browser.R
import com.orxeira.tv_browser.databinding.FragmentMainBinding
import com.orxeira.tv_browser.framework.AndroidPermissionChecker
import com.orxeira.tv_browser.framework.PlayServicesLocationDataSource
import com.orxeira.tv_browser.framework.database.TvShowRoomDataSource
import com.orxeira.tv_browser.framework.server.TvShowServerDataSource
import com.orxeira.tv_browser.ui.common.app
import com.orxeira.tv_browser.ui.common.launchAndCollect

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels {
        val application = requireActivity().app
        val repository = TvShowRepository(
            RegionRepository(
                PlayServicesLocationDataSource(application),
                AndroidPermissionChecker(application)
            ),
            TvShowRoomDataSource(application.db.tvShowDao()),
            TvShowServerDataSource(requireActivity().app.getString(R.string.api_key))
        )
        MainViewModelFactory(
            com.orxeira.usecases.GetPopularTvShowsUseCase(repository),
            com.orxeira.usecases.RequestPopularTvShowsUseCase(repository)
        )
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
            binding.error = it.error?.let(mainState::errorToString)
        }

        mainState.requestLocationPermission {
            viewModel.onUiReady()
        }
    }
}