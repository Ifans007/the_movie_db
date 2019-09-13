package com.example.themoviedb.database.repositories

import androidx.paging.LivePagedListBuilder
import com.example.themoviedb.database.DatabaseApp
import com.example.themoviedb.database.cache.MoviesCache
import com.example.themoviedb.database.databaseresults.MoviesResults
import com.example.themoviedb.fragments.popular.inflater.PopularBoundaryCallbacks

class PopularRepository {

    private val DATABASE_PAGE_SIZE = 60

    private val popularDao = DatabaseApp.getInstance().moviesDao()
    private val popularCache = MoviesCache(popularDao)

    fun popular(): MoviesResults {

        val dataSourceFactory = popularCache.getAllPopular()

        val boundaryCallback =
            PopularBoundaryCallbacks(popularCache)
        val networkErrors = boundaryCallback.networkErrors

        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()
        return MoviesResults(data, networkErrors)
    }
}