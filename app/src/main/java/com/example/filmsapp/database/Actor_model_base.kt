package com.example.filmsapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "actor_database")
class Actor_model_base (
    @PrimaryKey
    var id: Int= 0,
    var imdb_id: String = "",
    var profile_path: String = "",
    var name: String = "",
    var birthday: String = "",
    var place_of_birth: String = "",
    var biography: String = ""

    ): Serializable