package com.example.themoviedb.database.repositories

import androidx.paging.LivePagedListBuilder
import com.example.themoviedb.database.cache.moviescategory.PopularMoviesCache
import com.example.themoviedb.database.databaseresults.MoviesResults
import com.example.themoviedb.fragments.popular.inflater.PopularBoundaryCallbacks

class PopularRepository {

    private val DATABASE_PAGE_SIZE = 60

    private val popularMoviesCache = PopularMoviesCache

    fun popular(): MoviesResults {

        val boundaryCallback = PopularBoundaryCallbacks()

        val dataSourceFactory = popularMoviesCache.getAllPopularMoviesId()

        val networkErrors = boundaryCallback.networkErrors

        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()
        return MoviesResults(data, networkErrors)
    }
}