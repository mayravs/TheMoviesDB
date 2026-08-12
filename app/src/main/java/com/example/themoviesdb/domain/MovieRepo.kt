package com.example.themoviesdb.domain

import com.example.themoviesdb.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepo {
    suspend fun getMoviesNowPlaying(): Flow<List<Movie>>

    suspend fun refreshMoviesNowPlaying()

    fun getMovieById(id: Int): Flow<Movie?>
}