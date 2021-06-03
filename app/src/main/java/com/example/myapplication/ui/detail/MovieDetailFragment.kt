package com.example.myapplication.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.FragmentMovieDetailBinding
import com.example.myapplication.model.Movie.Companion.POSTER_URL
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    companion object {
        fun createInstance(): Fragment {
            return MovieDetailFragment()
        }
    }

    lateinit var binding: FragmentMovieDetailBinding

    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().intent.extras?.getInt(DetailActivity.DATA_EXTRA)?.let { movieId ->
            detailViewModel.loadMovieDetail(movieId)
        }

        lifecycleScope.launchWhenStarted {
            detailViewModel.viewState.collect {
                if (it.isLoading) {
                    binding.movieDetailProgressBar.visibility = View.VISIBLE
                    binding.contentView.visibility = View.GONE
                } else {
                    binding.movieDetailProgressBar.visibility = View.GONE
                    binding.contentView.visibility = View.VISIBLE
                    it.movie?.let { movie ->
                        binding.apply {
                            releaseDate.text = movie.release_date
                            popularity.text = movie.popularity.toString()
                            title.text = movie.title
                            overview.text = movie.overview
                            rating.text = movie.vote_average.toString()
                            Glide.with(requireContext()).load(POSTER_URL + movie.poster_path)
                                .into(moviePoster)
                        }
                    }
                }
            }
        }
    }
}
