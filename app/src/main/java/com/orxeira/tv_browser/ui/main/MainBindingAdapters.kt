package com.orxeira.tv_browser.ui.main

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.orxeira.domain.TvShow

@BindingAdapter("items")
fun RecyclerView.setItems(tvShows: List<TvShow>?) {
    if (tvShows != null) {
        (adapter as? TvShowAdapter)?.submitList(tvShows)
    }
}