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
) : Parcelable
