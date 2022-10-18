package com.example.themoviesdb.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.themoviesdb.R
import com.example.themoviesdb.databinding.FragmentMovieDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    private val mainViewModel by viewModels<MainViewModel>()
    private val args by navArgs<MovieDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentMovieDetailsBinding>(
            inflater,
            R.layout.fragment_movie_details,
            container,
            false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = mainViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initUI()
    }

    private fun initUI() {
        with(binding){
            tvMovieTitle.text = args.movie.title
            tvMovieOverview.text = args.movie.overview
            tvReleaseDate.text = "Released on: " + args.movie.release_date
            rbVoteAverage.rating = (args.movie.vote_average.toFloat() * 5)/10
            val imageUrl = "https://image.tmdb.org/t/p/w780/${args.movie.backdrop_path}"
            Glide.with(requireActivity()).load(imageUrl).into(ivBackdrop)
        }
    }

    private fun initViewModel() {
        mainViewModel.movies.observe(viewLifecycleOwner, Observer {
        })
    }
}