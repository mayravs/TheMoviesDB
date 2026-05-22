package com.example.themoviesdb.di

import com.example.themoviesdb.domain.MovieRepo
import com.example.themoviesdb.data.repository.MovieRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoriesModule {

    @Provides
    @Singleton
    fun providesMovieRepo(impl: MovieRepoImpl): MovieRepo = impl
}