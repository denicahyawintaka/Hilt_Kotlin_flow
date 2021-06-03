package com.example.myapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(ViewState.initialValue())
    val viewState: StateFlow<ViewState> = _viewState

    fun loadMovieData() {
        movieRepository.fetchMovie()
            .flowOn(Dispatchers.IO)
            .onStart {
                _viewState.emit(
                    _viewState.value.copy(
                        isLoading = true,
                    )
                )
            }
            .onEach {
                _viewState.emit(
                    _viewState.value.copy(
                        isLoading = false,
                        movies = it,
                    )
                )
            }
            .catch {
                _viewState.emit(
                    _viewState.value.copy(
                        isLoading = false,
                        error = true
                    )
                )
            }
            .launchIn(viewModelScope)

    }

    fun loadTvShowData() {
        movieRepository.fetchTvShow()
            .flowOn(Dispatchers.IO)
            .onStart {
                _viewState.emit(
                    _viewState.value.copy(
                        isLoading = true,
                    )
                )
            }
            .onEach {
                _viewState.emit(
                    _viewState.value.copy(
                        isLoading = false,
                        tvShows = it,
                    )
                )
            }
            .catch {
                _viewState.emit(
                    _viewState.value.copy(
                        isLoading = false,
                        error = true
                    )
                )
            }
            .launchIn(viewModelScope)
    }
}
