package com.example.filmsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Actor_model_base::class], version = 1, exportSchema = false)
abstract class ActorDataBase : RoomDatabase() {


    abstract fun allActors(): ActorDao


    companion object {
        @Volatile
        var INSTANCE: ActorDataBase? = null

        fun getDatabaseInstance(context: Context): ActorDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this)
            {
                val roomDataBaseinstance = Room.databaseBuilder(context,
                    ActorDataBase::class.java, "actors"
                ).allowMainThreadQueries().build()
                INSTANCE = roomDataBaseinstance
                return return roomDataBaseinstance
            }

        }

    }


}