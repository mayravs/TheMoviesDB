package com.example.themoviesdb.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviesdb.domain.usecase.GetMoviesUseCase
import com.example.themoviesdb.domain.usecase.RefreshMoviesUseCase
import com.example.themoviesdb.ui.list.MovieUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val refreshMoviesUseCase: RefreshMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MovieUiState>(MovieUiState.Loading)
    val uiState: StateFlow<MovieUiState> = _uiState.asStateFlow()

    init {
        observeMovies()
        refreshMoviesNowPlaying()
    }

    private fun observeMovies() {
        viewModelScope.launch {
            getMoviesUseCase()
                .catch { e ->
                    _uiState.value = MovieUiState.Error(e.message ?: "Unknown Error")
                }
                .collect { movies ->
                    if (movies.isNotEmpty()) {
                        _uiState.value = MovieUiState.Success(movies)
                    }
                }
        }
    }

    fun refreshMoviesNowPlaying() {
        viewModelScope.launch {
            try {
                refreshMoviesUseCase()
            } catch (e: Exception) {
                if (_uiState.value is MovieUiState.Loading) {
                    _uiState.value = MovieUiState.Error(e.message ?: "Unknown Error")
                }
            }
        }
    }
}