package com.example.themoviesdb.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.themoviesdb.R
import com.example.themoviesdb.domain.model.Movie
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.themoviesdb.ui.theme.TheMoviesDBTheme
import kotlin.math.floor
import kotlin.math.ceil

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    detailsViewModel: MovieDetailsViewModel = hiltViewModel()
) {
    val uiState by detailsViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    (uiState as? MovieDetailsUiState.Success)?.let {
                        Text(
                            text = it.movie.title,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onBackPressed() },
                        content = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Navigate back button"
                            )
                        }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.primary
    ) { padding ->

        when (uiState) {
            is MovieDetailsUiState.Loading -> {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                    content = {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                )
            }

            is MovieDetailsUiState.Error -> {
                val msg = (uiState as MovieDetailsUiState.Error).message

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.error, msg),
                        color = Color.Black
                    )
                }
            }

            is MovieDetailsUiState.Success -> {
                Surface(
                    modifier = modifier
                        .padding(padding)
                        .background(color = MaterialTheme.colorScheme.primary)
                ) {
                    MovieDetailContent(
                        movie = (uiState as MovieDetailsUiState.Success).movie
                    )
                }
            }
        }
    }
}

@Composable
fun MovieDetailContent(
    movie: Movie
) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primary)
        ) {
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.backdropImageUrl)
                    .crossfade(true)
                    .build()
            )

            Image(
                painter = painter,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .wrapContentHeight(align = Alignment.CenterVertically)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.release_date, movie.releaseDate),
                    color = MaterialTheme.colorScheme.onPrimary
                )

                RatingBar(rating = movie.movieRating)

                Text(
                    text = movie.overview,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
fun RatingBar(
    rating: Double
) {
    val stars = 5
    val starsColor = Color.Cyan

    val filledStars = floor(rating).toInt()
    val unfilledStars = (stars - ceil(rating)).toInt()
    val halfStar = rating.rem(1) != 0.0

    Row {
        repeat(filledStars) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Filled star rating",
                tint = starsColor
            )
        }

        if (halfStar) {
            Icon(
                painter = painterResource(R.drawable.ic_star_half),
                contentDescription = "Half-filled star rating",
                tint = starsColor
            )
        }

        repeat(unfilledStars) {
            Icon(
                painter = painterResource(R.drawable.ic_star_unfilled),
                contentDescription = "Unfilled star rating",
                tint = starsColor
            )
        }
    }
}

@Preview
@Composable
fun MovieDetailContentPreview() {
    TheMoviesDBTheme {
        MovieDetailContent(
            movie = Movie(
                id = 12,
                title = "The Orphan",
                overview = "After escaping from an Estonian psychiatric facility, Leena Klammer travels to America by impersonating Esther, the missing daughter of a wealthy family. But when her mask starts to slip, she is put against a mother who will protect her family from the murderous “child” at any cost.",
                voteAverage = 3.5,
                releaseDate = "2022-07-27",
                posterPath = "",
                backdropPath = "https://image.tmdb.org/t/p/w780//5GA3vV1aWWHTSDO5eno8V5zDo8r.jpg"
            )
        )
    }
}