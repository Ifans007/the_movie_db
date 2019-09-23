package com.example.themoviedb.database.cache.moviescategory

import androidx.paging.DataSource
import com.example.themoviedb.database.DatabaseApp
import com.example.themoviedb.database.entities.moviescategory.MovieCategoryIdTable
import kotlinx.coroutines.coroutineScope

object MovieCategoryIdListCache {

    private val databaseMovies = DatabaseApp.getInstance()

    private var counter = 0

    private lateinit var movieCategory: String

    fun getAllMovieCategoryId(movieCategory: String): DataSource.Factory<Int, MovieCategoryIdTable> {
        this.movieCategory = movieCategory
        return databaseMovies.movieCategoryDao().getAllMovieCategoryId(movieCategory)
    }

     @Synchronized suspend fun insert(
         listMoviesId: List<Int>,
         movieCategory: String
     ) {

         val movieCategoryIdIdList: MutableList<MovieCategoryIdTable> = mutableListOf()

         for (movieId in listMoviesId) {

             val movieCategoryIdTable = MovieCategoryIdTable()

             movieCategoryIdTable.counter = counter
             movieCategoryIdTable.movieId = movieId
             movieCategoryIdTable.category = movieCategory

             movieCategoryIdIdList.add(movieCategoryIdTable)

             ++counter
         }

         coroutineScope { databaseMovies.movieCategoryDao().insert(movieCategoryIdIdList) }
    }
}