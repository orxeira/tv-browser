package com.orxeira.tv_browser.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.orxeira.data.RegionRepository
import com.orxeira.data.TvShowRepository
import com.orxeira.tv_browser.R
import com.orxeira.tv_browser.databinding.FragmentDetailBinding
import com.orxeira.tv_browser.framework.AndroidPermissionChecker
import com.orxeira.tv_browser.framework.PlayServicesLocationDataSource
import com.orxeira.tv_browser.framework.database.TvShowRoomDataSource
import com.orxeira.tv_browser.framework.server.TvShowServerDataSource
import com.orxeira.tv_browser.ui.common.app
import com.orxeira.tv_browser.ui.common.launchAndCollect

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val safeArgs: DetailFragmentArgs by navArgs()

    private val viewModel: DetailViewModel by viewModels {
        val application = requireActivity().app
        val repository = TvShowRepository(
            RegionRepository(
                PlayServicesLocationDataSource(application),
                AndroidPermissionChecker(application)
            ),
            TvShowRoomDataSource(application.db.tvShowDao()),
            TvShowServerDataSource(requireActivity().app.getString(R.string.api_key))
        )
        DetailViewModelFactory(
            safeArgs.tvShowId,
            com.orxeira.usecases.FindTvShowUseCase(repository)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)

        binding.tvShowDetailToolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }

        viewLifecycleOwner.launchAndCollect(viewModel.state) { state ->
            if (state.tvShow != null)
                binding.tvShow = state.tvShow
        }
    }
}