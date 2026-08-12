package com.example.themoviesdb.data.model

import com.example.themoviesdb.data.local.MovieEntity
import com.google.gson.annotations.SerializedName

data class MoviesResponseDto(
    val results: List<MovieDto>
)

data class MovieDto (
    val id: Int,
    val title: String,
    val overview: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("vote_average") val voteAverage: Double
)

// Mapper function
fun MoviesResponseDto.toEntityList(): List<MovieEntity> {
    return this.results.map { dto ->
        MovieEntity(
            id = dto.id,
            title = dto.title,
            overview = dto.overview,
            voteAverage = dto.voteAverage,
            releaseDate = dto.releaseDate,
            posterPath = dto.posterPath,
            backdropPath = dto.backdropPath,
            lastUpdatedAt = System.currentTimeMillis()
        )
    }
}