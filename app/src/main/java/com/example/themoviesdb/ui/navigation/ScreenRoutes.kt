package com.example.themoviesdb.ui.navigation

sealed class Screen (val route: String) {

    object MovieList : Screen("MovieList")

    object Details: Screen("Details/{movie}")
}