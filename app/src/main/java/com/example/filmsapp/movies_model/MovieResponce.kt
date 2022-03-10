package com.example.filmsapp.movies_model

data class MovieResponce(
    val page: Int,
    val results: List<ResultMovie>,
    val total_pages: Int,
    val total_results: Int
)