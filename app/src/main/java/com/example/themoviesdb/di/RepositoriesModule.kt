package com.example.themoviesdb.di

import com.example.themoviesdb.data.repository.MovieRepo
import com.example.themoviesdb.data.repository.MovieRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoriesModule {

    @Binds
    abstract fun providesMovieRepo(impl: MovieRepoImpl): MovieRepo
}