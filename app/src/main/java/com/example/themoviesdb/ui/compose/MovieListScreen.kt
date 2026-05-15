package com.example.themoviesdb.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.themoviesdb.domain.model.Movie
import com.example.themoviesdb.ui.MainViewModel
import com.example.themoviesdb.ui.MovieUiState

@Composable
fun MovieListScreen(
    modifier: Modifier = Modifier,
    movieViewModel: MainViewModel = hiltViewModel(),
) {
    val uiState by movieViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { /* TODO */ }
    ) { padding ->

        when (uiState) {
            is MovieUiState.Loading -> {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                    content = {
                        CircularProgressIndicator()
                    }
                )
            }

            is MovieUiState.Error -> {
                val msg = (uiState as MovieUiState.Error).message
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Error: $msg")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { movieViewModel.getMoviesNowPlaying() }
                    ) {
                        Text(text = "Retry")
                    }
                }
            }

            is MovieUiState.Success -> {
                MoviesList(
                    modifier = modifier.padding(padding),
                    uiState = uiState as MovieUiState.Success
                )
            }
        }
    }
}

@Composable
fun MoviesList(
    uiState: MovieUiState.Success,
    modifier: Modifier = Modifier
) {
    val movies = uiState.movies
    LazyColumn(
        modifier = modifier
    ) {
        items(movies) { movie ->
            MovieItem(
                movie = movie,
                onClick = { /* TODO */ }
            )
        }
    }
}

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    poster: String,
    title: String,
    description: String
) {
    Surface(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Row() {
            /* TODO: Load image here */
            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(
                    text = title
                )
                Text(
                    text = description
                )
            }
        }
    }
}

@Preview
@Composable
fun MovieListScreenPreview() {
    MoviesList(
        uiState = MovieUiState.Success(
            listOf(
                Movie(
                    id = 121,
                    title = "The Super Mario Bros. Movie",
                    overview =  "While working underground to fix a water main, Brooklyn plumbers—and brothers—Mario and Luigi are transported down a mysterious pipe and wander into a magical new world. But when the brothers are separated, Mario embarks on an epic quest to find Luigi.",
                    voteAverage = 7.5,
                    releaseDate = "2023-04-05",
                    posterPath = "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
                    backdropPath = "/iJQIbOPm81fPEGKt5BPuZmfnA54.jpg"
                ),
                Movie(
                    id = 121,
                    title = "The Super Mario Bros. Movie",
                    overview =  "While working underground to fix a water main, Brooklyn plumbers—and brothers—Mario and Luigi are transported down a mysterious pipe and wander into a magical new world. But when the brothers are separated, Mario embarks on an epic quest to find Luigi.",
                    voteAverage = 7.5,
                    releaseDate = "2023-04-05",
                    posterPath = "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
                    backdropPath = "/iJQIbOPm81fPEGKt5BPuZmfnA54.jpg"
                )
            )
        )
    )
}