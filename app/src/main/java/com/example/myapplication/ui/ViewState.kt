package com.example.myapplication.ui

import com.example.myapplication.model.Movie

data class ViewState(
    val movies: List<Movie>
) {
    companion object {
        fun initialValue(): ViewState {
            return ViewState(
                movies = emptyList()
            )
        }
    }
}
