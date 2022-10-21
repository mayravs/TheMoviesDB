package com.example.themoviesdb.ui

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.themoviesdb.R
import com.example.themoviesdb.databinding.FragmentMovieDetailsBinding
import com.example.themoviesdb.domain.model.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    private var movie: Movie? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movie = arguments?.getParcelable("movie")
        binding = DataBindingUtil.inflate<FragmentMovieDetailsBinding>(
            inflater,
            R.layout.fragment_movie_details,
            container,
            false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        (activity as MainActivity).supportActionBar?.title = movie?.title
        with(binding){
            tvMovieOverview.text = movie?.overview
            val releaseDate = movie?.release_date
            tvReleaseDate.text = getString(R.string.release_date, releaseDate)
            rbVoteAverage.rating = (movie?.vote_average?.toFloat()?.times(5))?.div(10)!!
            val imageUrl = "https://image.tmdb.org/t/p/w780/${movie?.backdrop_path}"
            Glide.with(requireActivity()).load(imageUrl).into(ivBackdrop)
        }
    }
}