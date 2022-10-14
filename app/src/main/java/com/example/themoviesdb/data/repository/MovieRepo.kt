package com.example.themoviesdb.data.repository

import com.example.themoviesdb.domain.model.MoviesResponse
import retrofit2.Response

interface MovieRepo {
    suspend fun getMoviesNowPlaying(apiKey: String): MoviesResponse
}