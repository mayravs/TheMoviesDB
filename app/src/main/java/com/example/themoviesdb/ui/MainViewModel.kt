package com.example.themoviesdb.ui

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviesdb.domain.model.Movie
import com.example.themoviesdb.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    var movie: Movie? = null

    init {
        getMoviesNowPlaying()
    }

    fun getMoviesNowPlaying() {
        showProgressBar()
        viewModelScope.launch {
            try {
                // Used to display animation for user
                delay(600)
                val movies = getMoviesUseCase()
                _movies.value = movies
                hideProgressBar()
                Log.i("MainViewModel", "api call")
            } catch (e: Exception){
                Log.e("MainViewModel", "Encountered exception $e")
            }
        }
    }

    private fun showProgressBar() {
        _isLoading.value = true
    }

    private fun hideProgressBar() {
        _isLoading.value = false
    }
}