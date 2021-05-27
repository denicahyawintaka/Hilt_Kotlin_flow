package com.example.myapplication.repository

import com.example.myapplication.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchMovie(): Flow<List<Movie>>
}
