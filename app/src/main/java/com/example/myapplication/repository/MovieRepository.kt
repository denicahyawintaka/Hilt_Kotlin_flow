package com.example.myapplication.repository

import com.example.myapplication.model.Movie
import com.example.myapplication.model.TvShow
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchMovie(): Flow<List<Movie>>
    fun fetchTvShow(): Flow<List<TvShow>>
    fun fetchMovieDetail(id: Int): Flow<Movie>
    fun fetchTvShowDetail(id: Int): Flow<TvShow>
}
