package com.example.themoviedb.database.cache.moviescategory

import androidx.paging.DataSource
import com.example.themoviedb.database.DatabaseApp
import com.example.themoviedb.database.entities.moviescategory.PopularMoviesTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

object PopularMoviesCache {

    private val databaseMovies = DatabaseApp.getInstance()

    fun getAllPopularMoviesId(): DataSource.Factory<Int, PopularMoviesTable> {
        return databaseMovies.popularMoviesDao().getPopularMovies()
    }

    fun insert(popularMoviesIdList: MutableList<Int>) = runBlocking(Dispatchers.IO) {

        val popularMoviesTable = PopularMoviesTable()

        for (popularMoviesId in popularMoviesIdList) {

            popularMoviesTable.movieId = popularMoviesId

            databaseMovies.popularMoviesDao().insert(popularMoviesTable)
        }

    }
}