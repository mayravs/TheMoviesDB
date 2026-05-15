package com.example.themoviesdb.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviesdb.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MovieUiState>(MovieUiState.Loading)
    val uiState: StateFlow<MovieUiState> = _uiState.asStateFlow()

    fun isLoading(): Boolean = _uiState.value is MovieUiState.Loading

    init {
        getMoviesNowPlaying()
    }

    fun getMoviesNowPlaying() {
        viewModelScope.launch {
            try {
                val movies = getMoviesUseCase()
                _uiState.value = MovieUiState.Success(movies)
            } catch (e: Exception) {
                _uiState.value = MovieUiState.Error(e.message ?: "Unknown Error")
            }
        }
    }
}