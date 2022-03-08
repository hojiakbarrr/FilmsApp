package com.example.filmsapp.popular_movies_model

import com.example.filmsapp.popular_movies_model.Result

data class Movies(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)