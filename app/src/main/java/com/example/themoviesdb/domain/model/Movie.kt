package com.example.themoviesdb.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val vote_average: Double,
    val release_date: String,
    val poster_path: String,
    val backdrop_path: String
) : Parcelable {

    val posterImageUrl: String get() = "https://image.tmdb.org/t/p/w342/$poster_path"
    val backdropImageUrl: String get() = "https://image.tmdb.org/t/p/w780/$backdrop_path"
    val movieRating: Float get() = (vote_average.toFloat().times(5)).div(10)

}
