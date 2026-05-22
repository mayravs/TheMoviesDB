package com.example.themoviesdb.data.repository

import android.util.Log
import com.example.themoviesdb.BuildConfig
import com.example.themoviesdb.data.local.MovieDao
import com.example.themoviesdb.data.local.toDomainList
import com.example.themoviesdb.data.model.toEntityList
import com.example.themoviesdb.data.remote.MovieService
import com.example.themoviesdb.domain.MovieRepo
import com.example.themoviesdb.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepoImpl @Inject constructor (
    private val movieService: MovieService,
    private val movieDao: MovieDao
): MovieRepo {

    override suspend fun getMoviesNowPlaying(): Flow<List<Movie>> {
        return movieDao.getAllMovies().map { entities ->
            entities.toDomainList()
        }
    }

    override suspend fun refreshMoviesNowPlaying() {
        try {
            val response = movieService.getMoviesNowPlaying(BuildConfig.MOVIESDB_KEY)
            val entities = response.toEntityList()
            movieDao.insertAll(entities)
        } catch (e: Exception) {
            Log.e("Error","Error: $e")
        }
    }
}