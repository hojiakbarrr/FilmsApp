package com.example.filmsapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "movie_database")
data class Movie_model_base(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    @PrimaryKey
    var id: Int = 0,
    val original_language: String= "",
    val original_title: String= "",
    var overview: String= "",
    val popularity: Double= 0.0,
    var poster_path: String = "",
    var release_date: String = "",
    var title: String= "",
    val video: Boolean = false,
    var vote_average: Double = 0.0,
    val vote_count: Int = 0
): Serializable