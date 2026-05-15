package com.example.themoviesdb.domain

import com.example.themoviesdb.domain.model.MoviesResponse

interface MovieRepo {
    suspend fun getMoviesNowPlaying(): MoviesResponse
}