package com.example.themoviesdb.di

import android.content.Context
import com.example.themoviesdb.TheMoviesDBApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApplicationModuleProvider {

    @Provides
    @Singleton
    fun provideApplicationContext(moviesDBApp: TheMoviesDBApp): Context = moviesDBApp

    @Provides
    @Singleton
    fun retrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    fun provideRetrofit(retrofitBuilder: Retrofit.Builder): Retrofit {
        return TheMoviesDBApp.getMoviesDBRetrofit(retrofitBuilder)
    }
}