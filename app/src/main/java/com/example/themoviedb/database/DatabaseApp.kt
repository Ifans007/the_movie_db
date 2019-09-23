package com.example.themoviedb.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.themoviedb.database.dao.CommonInfoMoviesDao
import com.example.themoviedb.database.dao.detailsinfo.DetailsInfoMoviesDao
import com.example.themoviedb.database.dao.detailsinfo.additions.*
import com.example.themoviedb.database.dao.moviescategory.MovieCategoryIdDao
import com.example.themoviedb.database.entities.CommonInfoMoviesTable
import com.example.themoviedb.database.entities.detailsinfo.DetailsInfoMoviesTable
import com.example.themoviedb.database.entities.detailsinfo.additions.*
import com.example.themoviedb.database.entities.moviescategory.MovieCategoryIdTable

@Database(entities = [

    CommonInfoMoviesTable::class,

    MovieCategoryIdTable::class,

    DetailsInfoMoviesTable::class,

    BelongsToCollectionTable::class,
    GenreTable::class,
    ProductionCompanyTable::class,
    ProductionCountryTable::class,
    SpokenLanguageTable::class],
    version = 1, exportSchema = false)

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

    abstract fun commonInfoMoviesDao(): CommonInfoMoviesDao

    abstract fun movieCategoryDao(): MovieCategoryIdDao

    abstract fun detailsInfoMoviesDao(): DetailsInfoMoviesDao

    abstract fun belongsToCollectionDao(): BelongsToCollectionDao
    abstract fun genreDao(): GenreDao
    abstract fun productionCompanyDao(): ProductionCompanyDao
    abstract fun productionCountryDao(): ProductionCountryDao
    abstract fun spokenLanguageDao(): SpokenLanguageDao


}