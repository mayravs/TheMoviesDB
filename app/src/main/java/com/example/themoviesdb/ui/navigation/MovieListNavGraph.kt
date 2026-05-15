package com.example.themoviesdb.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.themoviesdb.domain.model.Movie
import com.example.themoviesdb.ui.MovieDetailsScreen
import com.example.themoviesdb.ui.MovieListScreen

fun NavGraphBuilder.appNavGraph(
    navController: NavController
) {
    composable(Screen.MovieList.route) {
        MovieListScreen(
            onMovieClick = {
                navController.navigate(Screen.Details.route)
            }
        )
    }

    composable(Screen.Details.route) {
        MovieDetailsScreen(
            movie = Movie(
                id = 121,
                title = "The Super Mario Bros. Movie",
                overview =  "While working underground to fix a water main, Brooklyn plumbers—and brothers—Mario and Luigi are transported down a mysterious pipe and wander into a magical new world. But when the brothers are separated, Mario embarks on an epic quest to find Luigi.",
                voteAverage = 7.5,
                releaseDate = "2023-04-05",
                posterPath = "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
                backdropPath = "/iJQIbOPm81fPEGKt5BPuZmfnA54.jpg"
            )
        )
    }
}