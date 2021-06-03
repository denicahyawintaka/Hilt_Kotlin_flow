package com.example.myapplication.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(DetailViewState.initialValue())
    val viewState: StateFlow<DetailViewState> = _viewState

    fun loadMovieDetail(id: Int) {
        movieRepository.fetchMovieDetail(id)
            .flowOn(Dispatchers.IO)
            .onStart {
                _viewState.emit(
                    _viewState.value.copy(
                        isLoading = true
                    )
                )
            }
            .onEach { movie ->
                _viewState.emit(
                    _viewState.value.copy(
                        isLoading = false,
                        movie = movie
                    )
                )
            }
            .catch {
                _viewState.emit(
                    _viewState.value.copy(
                        isLoading = false,
                        isError = true
                    )
                )
            }
            .launchIn(viewModelScope)

    }

    fun loadTvShowDetail(id: Int) {
        movieRepository.fetchTvShowDetail(id)
            .flowOn(Dispatchers.IO)
            .onStart {
                _viewState.emit(
                    _viewState.value.copy(
                        isLoading = true
                    )
                )
            }
            .onEach { tvShow ->
                _viewState.emit(
                    _viewState.value.copy(
                        isLoading = false,
                        tvShow = tvShow
                    )
                )
            }
            .catch {
                _viewState.emit(
                    _viewState.value.copy(
                        isLoading = false,
                        isError = true
                    )
                )
            }
            .launchIn(viewModelScope)
    }
}
