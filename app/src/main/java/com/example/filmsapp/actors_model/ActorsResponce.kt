package com.example.filmsapp.actors_model

data class ActorsResponce(
    val page: Int,
    val results: List<Actor>,
    val total_pages: Int,
    val total_results: Int
)