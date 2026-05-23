package com.example.themoviesdb.ui.details

import com.example.themoviesdb.domain.model.Movie

sealed interface MovieDetailsUiState {
    object Loading : MovieDetailsUiState

    data class Success(val movie: Movie) : MovieDetailsUiState

    data class Error(val message: String) : MovieDetailsUiState
}