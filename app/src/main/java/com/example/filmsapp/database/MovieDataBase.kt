package com.example.filmsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Movie_model_base :: class], version = 1, exportSchema = false)
abstract class MovieDataBase: RoomDatabase() {
    abstract fun allMovies() : MovieDao


    companion object {
        @Volatile
        var INSTANCE: MovieDataBase? = null

        fun getDatabaseInstance(context: Context): MovieDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this)
            {
                val roomDataBaseinstance = Room.databaseBuilder(context,
                    MovieDataBase::class.java, "moviess"
                ).allowMainThreadQueries().build()
                INSTANCE = roomDataBaseinstance
                return return roomDataBaseinstance
            }

        }

    }

}


