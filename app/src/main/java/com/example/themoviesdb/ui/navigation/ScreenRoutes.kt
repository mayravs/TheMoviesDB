package com.example.themoviesdb.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {

    @Serializable
    object MovieList : Screen

    @Serializable
    data class Details(val movieId: Int) : Screen
}