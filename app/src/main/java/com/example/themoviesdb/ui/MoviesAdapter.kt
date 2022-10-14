package com.example.themoviesdb.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviesdb.BR
import com.example.themoviesdb.R
import com.example.themoviesdb.databinding.MovieItemBinding
import com.example.themoviesdb.domain.model.Movie

class MoviesAdapter : ListAdapter<Movie, MoviesAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.movie_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.setVariable(BR.movie, movie)
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