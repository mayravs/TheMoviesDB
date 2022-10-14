package com.example.themoviesdb.data.repository

import com.example.themoviesdb.data.web.service.MovieService
import com.example.themoviesdb.domain.model.MoviesResponse
import javax.inject.Inject

class MovieRepoImpl @Inject constructor (private val movieService: MovieService): MovieRepo{

    override suspend fun getMoviesNowPlaying(apiKey: String): MoviesResponse {
        return movieService.getMoviesNowPlaying(apiKey)
    }
}