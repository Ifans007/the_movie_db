package com.example.themoviedb.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.themoviedb.database.dao.MoviesDao
import com.example.themoviedb.database.dao.additions.*
import com.example.themoviedb.database.entities.MoviesTable

@Database(entities = [MoviesTable::class], version = 1, exportSchema = false)

abstract class DatabaseApp : RoomDatabase() {

    companion object {
        private val DATABASE_NAME: String = "TheMovieDB"

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

    abstract fun moviesDao(): MoviesDao
    abstract fun belongsToCollectionDao(): BelongsToCollectionDao
    abstract fun genreDao(): GenreDao
    abstract fun productionCompanyDao(): ProductionCompanyDao
    abstract fun productionCountryDao(): ProductionCountryDao
    abstract fun spokenLanguageDao(): SpokenLanguageDao
}