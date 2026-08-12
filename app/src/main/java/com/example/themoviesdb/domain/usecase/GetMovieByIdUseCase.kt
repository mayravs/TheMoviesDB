package com.example.themoviesdb.domain.usecase

import com.example.themoviesdb.domain.MovieRepo
import com.example.themoviesdb.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieByIdUseCase @Inject constructor(
    private val movieRepo: MovieRepo
) {
    operator fun invoke(movieId: Int): Flow<Movie?> {
        return movieRepo.getMovieById(movieId)
    }
}