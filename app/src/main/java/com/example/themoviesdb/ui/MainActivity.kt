package com.example.themoviesdb.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviesdb.R
import com.example.themoviesdb.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        ).apply {
            lifecycleOwner = this@MainActivity
            viewModel = mainViewModel
        }

        initRv()
        initViewModel()
    }

    private fun initRv() {
        moviesAdapter = MoviesAdapter()

        binding.rvMovies.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }

    private fun initViewModel() {
        with(mainViewModel) {
            movies.observe(this@MainActivity) {
                moviesAdapter.submitList(it)
                println(it.get(0).poster_path)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_movies, menu)
        return true
    }

    fun onRefreshAction(mi: MenuItem) {
        //TODO: Handle menu item refresh click here
        println("refresh clicked")
    }
}