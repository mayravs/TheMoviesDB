package com.example.themoviesdb.ui

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.themoviesdb.R
import com.example.themoviesdb.databinding.FragmentMovieListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {
    private lateinit var binding: FragmentMovieListBinding
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentMovieListBinding>(
            inflater,
            R.layout.fragment_movie_list,
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
        initRv()
        initViewModel()
        (activity as MainActivity).supportActionBar?.title = getString(R.string.app_name)
    }

    private fun initRv() {
        moviesAdapter = MoviesAdapter(MoviesAdapter.OnClickListener { movie ->
            val action =
                MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(movie)
            findNavController().navigate(action)
        })
        binding.rvMovies.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(
                this.context,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }

    private fun initViewModel() {
        with(mainViewModel) {
            movies.observe(viewLifecycleOwner) {
                moviesAdapter.submitList(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_movies, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.miRefresh -> {
                mainViewModel.getMoviesNowPlaying()
                binding.rvMovies.smoothScrollToPosition(0)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}