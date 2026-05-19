package com.example.themoviesdb.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.themoviesdb.domain.model.Movie

@Composable
fun MovieListScreen(
    modifier: Modifier = Modifier,
    movieViewModel: MainViewModel = hiltViewModel(),
    onMovieClick: (Movie) -> Unit
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
                    uiState = uiState as MovieUiState.Success,
                    onMovieClick = onMovieClick
                )
            }
        }
    }
}

@Composable
fun MoviesList(
    uiState: MovieUiState.Success,
    modifier: Modifier = Modifier,
    onMovieClick: (Movie) -> Unit
) {
    val movies = uiState.movies
    LazyColumn(
        modifier = modifier
    ) {
        items(movies) { movie ->
            MovieItem (
                movie = movie,
                onClick = { onMovieClick(movie) }
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieItem(
    movie: Movie,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(
                onClick = onClick
            ),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.posterImageUrl)
                    .crossfade(true)
                    .build()
            )
            Image(
                painter = painter,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = movie.title,
                    textAlign = TextAlign.Left,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = movie.overview,
                    textAlign = TextAlign.Left,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 5,
                )
            }
        }
    }
}

@Preview(showBackground = true)
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
        ),
        onMovieClick = {}
    )
}