package com.example.themoviesdb.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val vote_average: Double,
    val release_date: String,
    val poster_path: String,
    val backdrop_path: String)
