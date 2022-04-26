package com.orxeira.tv_browser.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.orxeira.tv_browser.R
import com.orxeira.tv_browser.databinding.ActivityMainBinding
import com.orxeira.tv_browser.model.TvShowRepository
import com.orxeira.tv_browser.ui.detail.DetailActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val moviesRepository by lazy { TvShowRepository(this) }

    private val adapter = TvShowAdapter {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.TV_SHOW, it)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recycler.adapter = adapter

        lifecycleScope.launch {
            adapter.submitList(moviesRepository.findTopRatedShows().results)
        }
    }
}