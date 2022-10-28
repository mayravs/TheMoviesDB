package com.example.themoviesdb

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory

@HiltAndroidApp
class TheMoviesDBApp : Application() {
}

