package com.example.themoviesdb.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.themoviesdb.domain.model.Movie

@Entity(tableName = "movies")
data class MovieEntity (
    @PrimaryKey val id: Int,
    val title: String,
    val overview: String,
    val voteAverage: Double,
    val releaseDate: String,
    val posterPath: String,
    val backdropPath: String,
    val lastUpdatedAt: Long
)

fun MovieEntity.toDomain(): Movie {
    return Movie(
        id = this.id,
        title = this.title,
        overview = this.overview,
        voteAverage = this.voteAverage,
        releaseDate = this.releaseDate,
        posterPath = this.posterPath,
        backdropPath = this.backdropPath
    )
}

fun List<MovieEntity>.toDomainList(): List<Movie> = map { it.toDomain() }