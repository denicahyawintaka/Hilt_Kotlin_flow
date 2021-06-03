package com.example.myapplication.network

import com.example.myapplication.model.Movie
import com.example.myapplication.model.MovieResponse
import com.example.myapplication.model.TvShow
import com.example.myapplication.model.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("movie/now_playing")
    suspend fun fetchMovies(): MovieResponse

    @GET("movie/{id}")
    suspend fun fetchMovieDetail(@Path("id") id: Int): Movie

    @GET("tv/top_rated")
    suspend fun fetchTvShow(): TvShowResponse

    @GET("tv/{id}")
    suspend fun fetchTvShowDetail(@Path("id") id: Int): TvShow
}
