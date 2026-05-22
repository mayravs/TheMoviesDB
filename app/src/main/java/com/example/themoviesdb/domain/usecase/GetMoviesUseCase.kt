package com.example.themoviesdb.domain.usecase

import com.example.themoviesdb.domain.MovieRepo
import com.example.themoviesdb.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val movieRepo: MovieRepo) {

    suspend operator fun invoke(): Flow<List<Movie>> {
        return movieRepo.getMoviesNowPlaying()
    }
}