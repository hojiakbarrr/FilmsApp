package com.example.filmsapp.database

import androidx.room.*


@Dao
interface ActorDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun add_actor(actor: Actor_model_base)

    @Delete
    fun delete_Actor(actor: Actor_model_base)

    @Query("select * from actor_database")
    fun getAllActor(): MutableList<Actor_model_base>?

    @Query("select * from actor_database where imdb_id ==:ceId")
    fun getMoviesId(ceId: String): Actor_model_base
}