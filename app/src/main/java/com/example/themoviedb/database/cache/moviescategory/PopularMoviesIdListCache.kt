package com.example.themoviedb.database.cache.moviescategory

import androidx.paging.DataSource
import com.example.themoviedb.database.DatabaseApp
import com.example.themoviedb.database.entities.moviescategory.PopularMoviesIdTable
import com.example.themoviedb.retrofitservice.requests.models.CommonInfoMoviesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object PopularMoviesIdListCache {

    private val databaseMovies = DatabaseApp.getInstance()

    fun getAllPopularMoviesId(): DataSource.Factory<Int, PopularMoviesIdTable> {
        return databaseMovies.popularMoviesDao().getPopularMovies()
    }

     @Synchronized fun insert(results: List<CommonInfoMoviesModel>) = runBlocking {

        val popularMoviesIdIdList: MutableList<PopularMoviesIdTable> = mutableListOf()

        for (popularMovies in results) {

            val popularMoviesTable = PopularMoviesIdTable()

            popularMoviesTable.movieId = popularMovies.movieId

            popularMoviesIdIdList.add(popularMoviesTable)
        }

         launch(Dispatchers.IO) {
             databaseMovies.popularMoviesDao().insert(popularMoviesIdIdList)
         }.join()

    }


}