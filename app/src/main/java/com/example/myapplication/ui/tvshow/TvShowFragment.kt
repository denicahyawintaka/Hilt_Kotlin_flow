package com.example.myapplication.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.FragmentTvshowBinding
import com.example.myapplication.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class TvShowFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var binding: FragmentTvshowBinding

    private lateinit var tvShowAdapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvshowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvShowAdapter = TvShowAdapter()
        binding.rvTvShow.adapter = tvShowAdapter

        mainViewModel.loadTvShowData()

        lifecycleScope.launchWhenStarted {
            mainViewModel.viewState.collect {
                when {
                    it.isLoading -> {
                        binding.tvShowProgressBar.visibility = View.VISIBLE
                        binding.errorText.visibility = View.GONE
                        binding.rvTvShow.visibility = View.GONE
                    }
                    it.error -> {
                        binding.tvShowProgressBar.visibility = View.GONE
                        binding.errorText.visibility = View.VISIBLE
                    }
                    else -> {
                        binding.errorText.visibility = View.GONE
                        binding.tvShowProgressBar.visibility = View.GONE
                        binding.rvTvShow.visibility = View.VISIBLE
                        tvShowAdapter.submitList(it.tvShows)
                    }
                }
            }
        }
    }
}
