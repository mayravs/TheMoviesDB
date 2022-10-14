package com.example.themoviesdb.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String){
    val imageUrl = "https://image.tmdb.org/t/p/w342/$url"
    Glide.with(this).load(imageUrl).into(this)
}