package com.example.myapplication.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.FragmentTvshowDetailBinding
import com.example.myapplication.model.Movie.Companion.POSTER_URL
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class TvShowDetailFragment : Fragment() {

    companion object {
        fun createInstance(): Fragment {
            return TvShowDetailFragment()
        }
    }

    private lateinit var binding: FragmentTvshowDetailBinding

    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvshowDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().intent.extras?.getInt(DetailActivity.DATA_EXTRA)?.let { tvShowId ->
            detailViewModel.loadTvShowDetail(tvShowId)
        }

        lifecycleScope.launchWhenStarted {
            detailViewModel.viewState.collect {
                if (it.isLoading) {
                    binding.tvShowDetailProgressBar.visibility = View.VISIBLE
                    binding.contentView.visibility = View.GONE
                } else {
                    binding.tvShowDetailProgressBar.visibility = View.GONE
                    binding.contentView.visibility = View.VISIBLE
                    it.tvShow?.let { tvShow ->
                        binding.apply {
                            releaseDate.text = tvShow.first_air_date
                            popularity.text = tvShow.popularity.toString()
                            title.text = tvShow.name
                            overview.text = tvShow.overview
                            rating.text = tvShow.vote_average.toString()
                            Glide.with(requireContext()).load(POSTER_URL + tvShow.poster_path)
                                .into(moviePoster)
                        }
                    }
                }
            }
        }
    }
}
