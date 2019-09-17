package com.example.themoviedb

import android.widget.Toast
import com.example.themoviedb.database.DatabaseApp
import com.example.themoviedb.database.cache.detailsinfo.additions.GenreCache
import com.example.themoviedb.retrofitservice.requests.GetRequest
import com.example.themoviedb.retrofitservice.requests.models.GenresRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class InitDatabase(private val mainActivity: MainActivity) {

    init {

        runBlocking {

            val database = initDataBase()

            val isEmpty = async(Dispatchers.IO) { database.genreDao().getAll().isEmpty() }.await()

            if (isEmpty) {

                requestGenresList({request -> cacheGenresList(database, request) })
            }
        }
    }

    private fun initDataBase(): DatabaseApp {
        return DatabaseApp.getInstance(mainActivity)
    }

    private fun requestGenresList(finished: (genresRequest: GenresRequest?) -> Unit) {

        GetRequest.getGenresList(
            {
                finished(it)
            },
            {
                Toast.makeText(mainActivity, "Failed to load genre list: $it.", Toast.LENGTH_SHORT).show()
                finished(null)
            })
    }

    private fun cacheGenresList(dataBase: DatabaseApp, requestGenresList: GenresRequest?) {

        if (requestGenresList?.genresList != null) {

            GenreCache.insert(dataBase.genreDao(), requestGenresList.genresList)

        }
    }
}