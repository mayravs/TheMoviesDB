package com.example.themoviesdb.domain.usecase

import com.example.themoviesdb.data.repository.MovieRepo
import com.example.themoviesdb.domain.model.Movie
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val movieRepo: MovieRepo) {

    suspend operator fun invoke(): List<Movie> {
        val moviesResponse = movieRepo.getMoviesNowPlaying()
        return moviesResponse.results.sortedByDescending { it.release_date }
    }

}