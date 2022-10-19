package com.example.themoviesdb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviesdb.domain.model.Movie
import com.example.themoviesdb.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    init {
        getMoviesNowPlaying()
    }

    fun getMoviesNowPlaying() {
        viewModelScope.launch {
            if (_movies.value == null) {
                val movies = getMoviesUseCase()
                _movies.value = movies
            }
        }
    }
}
