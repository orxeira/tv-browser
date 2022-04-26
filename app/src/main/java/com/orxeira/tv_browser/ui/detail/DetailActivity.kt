package com.orxeira.tv_browser.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.orxeira.tv_browser.databinding.ActivityDetailBinding
import com.orxeira.tv_browser.model.TvShow
import com.orxeira.tv_browser.ui.common.loadUrl

class DetailActivity : AppCompatActivity() {
    companion object {
        const val TV_SHOW = "DetailActivity:tvshow"
    }

    private val viewModel: DetailViewModel by viewModels {
        DetailViewModelFactory(requireNotNull(intent.getParcelableExtra(TV_SHOW)))
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.state.observe(this) { updateUI(it.tvShow) }
    }

    private fun updateUI(tvShow: TvShow) = with(binding) {
        movieDetailToolbar.title = tvShow.name
        movieDetailImage.loadUrl("https://image.tmdb.org/t/p/w780${tvShow.posterPath ?: tvShow.backdropPath}")
        movieDetailSummary.text = tvShow.overview
    }
}