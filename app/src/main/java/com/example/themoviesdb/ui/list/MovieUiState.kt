package com.example.themoviesdb.ui.list

import com.example.themoviesdb.domain.model.Movie

sealed class MovieUiState {

    object Loading : MovieUiState()

    data class Success(val movies: List<Movie>) : MovieUiState()

    data class Error(val message: String) : MovieUiState()
}