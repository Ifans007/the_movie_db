package com.example.themoviedb.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.themoviedb.database.dao.PopularDao
import com.example.themoviedb.database.entities.PopularTable

@Database(entities = [PopularTable::class], version = 1, exportSchema = false)

abstract class DatabaseApp : RoomDatabase() {

    companion object {
        private val DATABASE_NAME: String = "movie"

        @Volatile
        private var instance: DatabaseApp? = null

        fun getInstance(context: Context? = null): DatabaseApp {
            if (instance == null) {

                instance = Room.databaseBuilder(
                    context!!.applicationContext,
                    DatabaseApp::class.java,
                    DATABASE_NAME)
                    .build()
            }
            return instance!!
        }
    }

    abstract fun popularDao(): PopularDao
}