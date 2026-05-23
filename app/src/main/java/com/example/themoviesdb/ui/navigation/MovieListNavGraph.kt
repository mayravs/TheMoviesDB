package com.example.themoviesdb.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.themoviesdb.ui.details.MovieDetailsScreen
import com.example.themoviesdb.ui.list.MovieListScreen

fun NavGraphBuilder.appNavGraph(
    navController: NavController
) {
    composable<Screen.MovieList> {
        MovieListScreen(
            onMovieClick = { movieId ->
                navController.navigate(Screen.Details(movieId))
            }
        )
    }

    composable<Screen.Details> {
        MovieDetailsScreen()
    }
}