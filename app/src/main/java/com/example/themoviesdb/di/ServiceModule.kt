package com.example.themoviesdb.di

import com.example.themoviesdb.data.web.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class ServiceModule {

    @Provides
    fun providesMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }
}