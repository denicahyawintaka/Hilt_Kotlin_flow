package com.example.myapplication.model

data class TvShow(
    val id: String,
    val url: String,
    val name: String,
    val runtime: String,
    val language: String,
    val genres: List<String>,
    val image: String
)
