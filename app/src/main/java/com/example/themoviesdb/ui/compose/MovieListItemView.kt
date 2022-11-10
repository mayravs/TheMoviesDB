package com.example.themoviesdb.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.themoviesdb.domain.model.Movie

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieListItemView(movie: Movie, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        elevation = 9.dp,
        shape = RoundedCornerShape(
            topStart = 10.dp,
            topEnd = 10.dp,
            bottomStart = 10.dp,
            bottomEnd = 10.dp
        ),
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .padding(vertical = 20.dp)
    ) {
        Row(Modifier.fillMaxWidth()) {
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
                modifier = Modifier
                    .height(180.dp)
            )
            Column(Modifier.fillMaxWidth()) {
                Text(
                    text = movie.title,
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .padding(top = 8.dp)
                )
                Text(
                    text = movie.overview,
                    textAlign = TextAlign.Left,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body1,
                    maxLines = 5,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .padding(top = 8.dp)
                )
            }
        }
    }
}