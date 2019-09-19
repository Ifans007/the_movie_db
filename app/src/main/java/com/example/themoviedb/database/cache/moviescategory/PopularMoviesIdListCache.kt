package com.example.themoviedb.database.cache.moviescategory

import androidx.paging.DataSource
import com.example.themoviedb.database.DatabaseApp
import com.example.themoviedb.database.entities.moviescategory.PopularMoviesIdTable
import kotlinx.coroutines.coroutineScope

object PopularMoviesIdListCache {

    private val databaseMovies = DatabaseApp.getInstance()

    private var counter = 0

    fun getAllPopularMoviesId(): DataSource.Factory<Int, PopularMoviesIdTable> {
        return databaseMovies.popularMoviesDao().getPopularMovies()
    }

     @Synchronized suspend fun insert(listMoviesId: List<Int>) {

         val popularMoviesIdIdList: MutableList<PopularMoviesIdTable> = mutableListOf()

         for (movieId in listMoviesId) {

             val popularMoviesTable = PopularMoviesIdTable()

             popularMoviesTable.counter = counter
             popularMoviesTable.movieId = movieId

             popularMoviesIdIdList.add(popularMoviesTable)

             ++counter
         }

         coroutineScope { databaseMovies.popularMoviesDao().insert(popularMoviesIdIdList) }
    }
}