package com.orxeira.tv_browser.ui.main

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.orxeira.tv_browser.model.TvShow
import kotlinx.coroutines.CoroutineScope

fun Fragment.buildMainState(
    scope: CoroutineScope = viewLifecycleOwner.lifecycleScope,
    navController: NavController = findNavController(),
) = MainState(scope, navController)

class MainState(
    private val scope: CoroutineScope,
    private val navController: NavController
) {
    fun onTvShowClicked(tvShow: TvShow) {
        val action = MainFragmentDirections.actionMainToDetail(tvShow)
        navController.navigate(action)
    }
}
