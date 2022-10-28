package com.example.themoviesdb.binding

import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("posterImageUrl")
fun ImageView.loadPosterImage(url: String?){
    Glide.with(this).load(url).into(this)
}

@BindingAdapter("backdropImageUrl")
fun ImageView.loadBackdropImage(url: String?){
    Glide.with(this).load(url).into(this)
}

@BindingAdapter("refresh")
fun refresh(progressBar: ProgressBar, refresh: Boolean) {
    progressBar.isVisible = refresh
}