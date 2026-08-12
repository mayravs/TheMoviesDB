package com.example.themoviesdb.domain.usecase

import com.example.themoviesdb.domain.MovieRepo
import javax.inject.Inject

class RefreshMoviesUseCase @Inject constructor(
    private val movieRepo: MovieRepo
) {
    suspend operator fun invoke() {
        movieRepo.refreshMoviesNowPlaying()
    }
}