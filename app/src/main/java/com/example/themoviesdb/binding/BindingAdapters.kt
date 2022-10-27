package com.example.themoviesdb.binding

import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RatingBar
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("posterImageUrl")
fun ImageView.loadPosterImage(url: String){
    val imageUrl = "https://image.tmdb.org/t/p/w342/$url"
    Glide.with(this).load(imageUrl).into(this)
}

@BindingAdapter("backdropImageUrl")
fun ImageView.loadBackdropImage(url: String){
    val imageUrl = "https://image.tmdb.org/t/p/w780/$url"
    Glide.with(this).load(imageUrl).into(this)
}

@BindingAdapter("rating")
fun RatingBar.rating(rating: Double){
    this.rating = (rating.toFloat().times(5)).div(10)
}

@BindingAdapter("refresh")
fun refresh(progressBar: ProgressBar, refresh: Boolean) {
    progressBar.isVisible = refresh
}