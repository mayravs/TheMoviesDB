package com.example.themoviesdb.ui

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.themoviesdb.R
import com.example.themoviesdb.databinding.FragmentMovieDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    private val mainViewModel by activityViewModels<MainViewModel>()
    private val safeArgs: MovieDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val movie = safeArgs.movie
        mainViewModel.movie = movie
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
        (activity as MainActivity).supportActionBar?.title = mainViewModel.movie?.title
    }
}