package com.example.themoviedb

import android.widget.Toast
import com.example.themoviedb.database.DatabaseApp
import com.example.themoviedb.database.cache.detailsinfo.additions.GenreCache
import com.example.themoviedb.retrofitservice.requests.GetRequest
import com.example.themoviedb.retrofitservice.requests.models.GenresRequest
import com.example.themoviedb.ui.main.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

object InitDatabase {

    private lateinit var mainActivity: MainActivity

    suspend fun initDatabase(mainActivity: MainActivity) {
        this.mainActivity = mainActivity
        init()
    }

    private suspend fun init() {

        coroutineScope {

            val database = initDataBase()

            requestGenresList({request -> cacheGenresList(database, request) })
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
                Toast.makeText(mainActivity, "Failed to load genre list: $it", Toast.LENGTH_SHORT).show()
                finished(null)
            })
    }

    private fun cacheGenresList(dataBase: DatabaseApp, requestGenresList: GenresRequest?) {

        if (requestGenresList?.genresList != null) {

            GenreCache.insert(dataBase.genreDao(), requestGenresList.genresList)
        }
    }

    fun refresh() {
        CoroutineScope(Dispatchers.Main).launch { init() }
    }


}