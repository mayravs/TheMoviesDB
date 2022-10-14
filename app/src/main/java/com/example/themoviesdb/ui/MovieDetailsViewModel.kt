package com.example.themoviesdb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviesdb.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val getMoviesUseCase: GetMoviesUseCase) : ViewModel() {

//    private val _movieTitle = MutableLiveData<String>()
//    val movieTitle: LiveData<String> = _movieTitle
//
//    private val _movieOverview = MutableLiveData<String>()
//    val movieOverview: LiveData<String> = _movieOverview
//
//    private val _movieRating = MutableLiveData<Double>()
//    val movieRating: LiveData<Double> = _movie
//
//    private val _movieBackdrop = MutableLiveData<String>()
//    val movieBackdrop: LiveData<String> = _movieBackdrop
//
//    private val _movieReleaseDate = MutableLiveData<String>()
//    val movieReleaseDate: LiveData<String> = _movieReleaseDate
//
//    fun getMovies(){
//        viewModelScope.launch {
//            _movieTitle.value = getMoviesUseCase()
//        }
//    }
}