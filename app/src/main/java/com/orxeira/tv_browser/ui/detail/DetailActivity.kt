package com.orxeira.tv_browser.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.orxeira.tv_browser.databinding.ActivityDetailBinding
import com.orxeira.tv_browser.model.TvShow
import com.orxeira.tv_browser.ui.common.loadUrl

class DetailActivity : AppCompatActivity() {
    companion object {
        const val TV_SHOW = "DetailActivity:tvshow"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityDetailBinding.inflate(layoutInflater).run {
            setContentView(root)

            val tvShow = intent.getParcelableExtra<TvShow>(TV_SHOW) ?: throw IllegalStateException()

            movieDetailToolbar.title = tvShow.name

            val background = tvShow.backdropPath ?: tvShow.posterPath
            movieDetailImage.loadUrl("https://image.tmdb.org/t/p/w780$background")
            movieDetailSummary.text = tvShow.overview
        }
    }
}