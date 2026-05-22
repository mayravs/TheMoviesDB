package com.example.themoviesdb.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val voteAverage: Double,
    val releaseDate: String,
    val posterPath: String,
    val backdropPath: String
) {
    val posterImageUrl: String get() = "https://image.tmdb.org/t/p/w342/$posterPath"
    val backdropImageUrl: String get() = "https://image.tmdb.org/t/p/w780/$backdropPath"
    val movieRating: Double get() = (voteAverage.times(5)).div(10)
}
