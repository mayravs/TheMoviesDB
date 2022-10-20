package com.example.themoviesdb.data.web.service

import com.example.themoviesdb.domain.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    suspend fun getMoviesNowPlaying(@Query ("api_key") api_key: String) : MoviesResponse
}
