package com.example.myapplication.repository.impl

import com.example.myapplication.model.Movie
import com.example.myapplication.network.ApiService
import com.example.myapplication.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    MovieRepository {

    override fun fetchMovie(): Flow<List<Movie>> {
        return flow {
            val response = apiService.fetchMovies().results
            emit(response)
        }
    }
}
