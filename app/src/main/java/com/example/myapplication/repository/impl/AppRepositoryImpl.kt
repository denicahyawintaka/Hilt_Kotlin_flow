package com.example.myapplication.repository.impl

import com.example.myapplication.model.Movie
import com.example.myapplication.model.TvShow
import com.example.myapplication.network.ApiService
import com.example.myapplication.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MovieRepository {

    override fun fetchMovie(): Flow<List<Movie>> {
        return flow {
            val response = apiService.fetchMovies().results
            emit(response)
        }
    }

    override fun fetchTvShow(): Flow<List<TvShow>> {
        return flow {
            val response = apiService.fetchTvShow().results
            emit(response)
        }
    }

    override fun fetchMovieDetail(id: Int): Flow<Movie> {
        return flow {
            val response = apiService.fetchMovieDetail(id)
            emit(response)
        }
    }

    override fun fetchTvShowDetail(id: Int): Flow<TvShow> {
        return flow {
            val response = apiService.fetchTvShowDetail(id)
            emit(response)
        }

    }
}
