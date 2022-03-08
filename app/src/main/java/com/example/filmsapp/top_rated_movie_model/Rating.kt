package com.example.filmsapp.top_rated_movie_model

data class Rating(
    val page: Int,
    val results: List<ResultX>,
    val total_pages: Int,
    val total_results: Int
)