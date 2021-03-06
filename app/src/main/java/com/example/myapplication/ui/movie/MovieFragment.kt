package com.example.myapplication.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.FragmentMovieBinding
import com.example.myapplication.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var binding: FragmentMovieBinding

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapter = MovieAdapter()
        binding.rvMovie.adapter = movieAdapter

        mainViewModel.loadMovieData()

        lifecycleScope.launchWhenStarted {
            mainViewModel.viewState.collect {
                when {
                    it.isLoading -> {
                        binding.movieProgressBar.visibility = View.VISIBLE
                        binding.errorText.visibility = View.GONE
                        binding.rvMovie.visibility = View.GONE
                    }
                    it.error -> {
                        binding.movieProgressBar.visibility = View.GONE
                        binding.errorText.visibility = View.VISIBLE
                    }
                    else -> {
                        binding.errorText.visibility = View.GONE
                        binding.movieProgressBar.visibility = View.GONE
                        binding.rvMovie.visibility = View.VISIBLE
                        movieAdapter.submitList(it.movies)
                    }
                }
            }
        }
    }
}
