package com.example.themoviesdb.data.repository

import com.example.themoviesdb.domain.model.MoviesResponse

interface MovieRepo {
    suspend fun getMoviesNowPlaying(): MoviesResponse
}