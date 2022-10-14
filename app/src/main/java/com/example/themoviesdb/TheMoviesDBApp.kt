package com.example.themoviesdb

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory

@HiltAndroidApp
class TheMoviesDBApp : Application() {

    companion object {
        private lateinit var moviesDBRetrofit: Retrofit

        fun getMoviesDBRetrofit(retrofitBuilder: Builder): Retrofit {
            return if (::moviesDBRetrofit.isInitialized) {
                moviesDBRetrofit
            } else {
                updateMoviesDBRetrofit(retrofitBuilder)
            }
        }

        private fun updateMoviesDBRetrofit(retrofitBuilder: Builder): Retrofit {
            moviesDBRetrofit = retrofitBuilder.baseUrl(BuildConfig.MOVIESDB_URL).build()
            return moviesDBRetrofit
        }
    }
}

