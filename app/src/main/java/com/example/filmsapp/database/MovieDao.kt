package com.example.filmsapp.database

import androidx.room.*


@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun add_New_Movie(movie: Movie_model_base)

    @Delete
    fun delete_Movie(movie: Movie_model_base)

    @Query("select * from movie_database")
    fun getAllMovies(): MutableList<Movie_model_base>?

    @Query("select * from movie_database where  id ==:ceId")
    fun getMoviesId(ceId: String): Movie_model_base

}