package com.example.myapplication.ui

import com.example.myapplication.model.Movie
import com.example.myapplication.model.TvShow

data class ViewState(
    val movies: List<Movie>,
    val tvShows: List<TvShow>,
    val isLoading: Boolean,
    val error: Boolean
) {
    companion object {
        fun initialValue(): ViewState {
            return ViewState(
                isLoading = false,
                movies = emptyList(),
                tvShows = emptyList(),
                error = false
            )
        }
    }
}
