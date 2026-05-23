package com.example.themoviesdb.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.themoviesdb.R
import com.example.themoviesdb.domain.model.Movie
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.themoviesdb.ui.list.MovieUiState
import kotlin.math.floor
import kotlin.math.ceil

@Composable
fun MovieDetailsScreen(
    modifier: Modifier = Modifier,
    detailsViewModel: MovieDetailsViewModel = hiltViewModel()
) {
    val uiState by detailsViewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        is MovieDetailsUiState.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
                content = {
                    CircularProgressIndicator()
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
                Text(text = stringResource(R.string.error, msg))
            }
        }

        is MovieDetailsUiState.Success -> {
            Surface(
                modifier = modifier
            ) {
                MovieDetailContent(movie = (uiState as MovieDetailsUiState.Success).movie)
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
            modifier = Modifier.fillMaxWidth()
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
            Text(
                text = stringResource(id = R.string.release_date, movie.releaseDate),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .padding(top = 20.dp)
            )
            RatingBar(
                rating = movie.movieRating,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .padding(top = 5.dp)
            )
            Text(
                text = movie.overview,
                modifier = Modifier
                    .padding(all = 10.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun RatingBar(
    modifier: Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = Color.Magenta
) {
    val filledStars = floor(rating).toInt()
    val unfilledStars = (stars - ceil(rating)).toInt()
    val halfStar = rating.rem(1) != 0.0

    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = starsColor
            )
        }

        if (halfStar) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_star_half_24),
                contentDescription = null,
                tint = starsColor
            )
        }

        repeat(unfilledStars) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_star_outline_24),
                contentDescription = null,
                tint = starsColor
            )
        }
    }
}

@Preview
@Composable
fun MovieDetailContentPreview() {
    MovieDetailContent(
        movie = Movie(
            id =12,
            title ="The Orphan",
            overview = "After escaping from an Estonian psychiatric facility, Leena Klammer travels to America by impersonating Esther, the missing daughter of a wealthy family. But when her mask starts to slip, she is put against a mother who will protect her family from the murderous “child” at any cost.",
            voteAverage = 3.5,
            releaseDate = "2022-07-27",
            posterPath = "",
            backdropPath = "https://image.tmdb.org/t/p/w780//5GA3vV1aWWHTSDO5eno8V5zDo8r.jpg"
        )
    )
}