package com.example.myapplication.network

import com.example.myapplication.model.Response
import retrofit2.http.GET

interface ApiService {
    @GET("movie/now_playing")
    suspend fun fetchMovies(): Response
}
