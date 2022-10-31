package com.example.themoviesdb.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviesdb.domain.model.Movie
import com.example.themoviesdb.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
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

    private val _isSuccessful = MutableLiveData<Boolean>()
    val isSuccessful: LiveData<Boolean> get() = _isSuccessful

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        hideProgressBar()
        _isSuccessful.value = false
        Log.e("MainViewModel", "Encountered exception: $throwable")
    }

    var movie: Movie? = null

    init {
        getMoviesNowPlaying()
    }

    fun getMoviesNowPlaying() {
        showProgressBar()
        viewModelScope.launch(errorHandler) {
            delay(600)
            val movies = getMoviesUseCase()
            _movies.value = movies
            _isSuccessful.value = true
            hideProgressBar()
            Log.i("MainViewModel", "api call")
        }
    }

    private fun showProgressBar() {
        _isLoading.value = true
    }

    private fun hideProgressBar() {
        _isLoading.value = false
    }
}