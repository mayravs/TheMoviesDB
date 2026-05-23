package com.example.themoviesdb.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.themoviesdb.domain.usecase.GetMovieByIdUseCase
import com.example.themoviesdb.ui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    getMovieByIdUseCase: GetMovieByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val detailsRoute = savedStateHandle.toRoute<Screen.Details>()

    private val movieId: Int = detailsRoute.movieId

    val uiState: StateFlow<MovieDetailsUiState> = getMovieByIdUseCase(movieId).map { movie ->
        if (movie != null) {
            MovieDetailsUiState.Success(movie)
        } else {
            MovieDetailsUiState.Error("Movie not found")
        }
    }
    .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MovieDetailsUiState.Loading
    )
}