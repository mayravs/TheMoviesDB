package com.example.themoviesdb.ui

import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviesdb.domain.model.Movie
import com.example.themoviesdb.ui.compose.MovieListItemView

class MoviesAdapter : ListAdapter<Movie, MoviesAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ComposeView(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    class ViewHolder(composeView: ComposeView) : RecyclerView.ViewHolder(composeView) {

        fun bind(movie: Movie) {
            (itemView as ComposeView).setContent {
                MaterialTheme {
                    MovieListItemView(movie = movie) {
                        navigateToMovie(movie)
                    }
                }
            }
        }

        private fun navigateToMovie(movie: Movie) {
            val direction =
                MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(movie)
            itemView.findNavController().navigate(direction)
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return newItem == oldItem
        }
    }

}