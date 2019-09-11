package com.example.themoviedb.database.repositories

import androidx.paging.LivePagedListBuilder
import com.example.themoviedb.database.DatabaseApp
import com.example.themoviedb.database.cache.PopularCache
import com.example.themoviedb.database.databaseresults.PopularResults
import com.example.themoviedb.fragments.popular.inflater.PopularBoundaryCallbacks

class PopularRepository {

    private val DATABASE_PAGE_SIZE = 60

    private val popularDao = DatabaseApp.getInstance().popularDao()
    private val popularCache = PopularCache(popularDao)

    fun popular(): PopularResults {

        val dataSourceFactory = popularCache.getAllPopular()

        val boundaryCallback =
            PopularBoundaryCallbacks(popularCache)
        val networkErrors = boundaryCallback.networkErrors

        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()
        return PopularResults(data, networkErrors)
    }
}