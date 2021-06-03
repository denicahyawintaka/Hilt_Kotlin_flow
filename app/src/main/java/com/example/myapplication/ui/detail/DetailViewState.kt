package com.example.myapplication.ui.detail

import com.example.myapplication.model.Movie
import com.example.myapplication.model.TvShow

data class DetailViewState(
    val movie: Movie?,
    val tvShow: TvShow?,
    val isLoading: Boolean,
    val isError: Boolean
) {
    companion object {
        fun initialValue(): DetailViewState {
            return DetailViewState(
                isLoading = false,
                movie = null,
                tvShow = null,
                isError = false
            )
        }
    }
}
