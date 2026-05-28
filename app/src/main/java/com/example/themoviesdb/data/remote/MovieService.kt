package com.example.themoviesdb.data.remote

import com.example.themoviesdb.data.model.MoviesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("movie/now_playing?region=US")
    suspend fun getMoviesNowPlaying(@Query("api_key") apiKey: String) : MoviesResponseDto
}