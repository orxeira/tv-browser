package com.orxeira.tv_browser.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.orxeira.tv_browser.R
import com.orxeira.tv_browser.databinding.ViewTvShowBinding
import com.orxeira.tv_browser.model.TvShow
import com.orxeira.tv_browser.ui.common.basicDiffUtil
import com.orxeira.tv_browser.ui.common.inflate
import com.orxeira.tv_browser.ui.common.loadUrl

class TvShowAdapter(private val listener: (TvShow) -> Unit) :
    ListAdapter<TvShow, TvShowAdapter.ViewHolder>(basicDiffUtil { old, new -> old.id == new.id }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_tv_show, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = getItem(position)
        holder.bind(tvShow)
        holder.itemView.setOnClickListener { listener(tvShow) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewTvShowBinding.bind(view)
        fun bind(tvShow: TvShow) = with(binding) {
            movieTitle.text = tvShow.name
            movieCover.loadUrl("https://image.tmdb.org/t/p/w185/${tvShow.posterPath}")
        }
    }
}