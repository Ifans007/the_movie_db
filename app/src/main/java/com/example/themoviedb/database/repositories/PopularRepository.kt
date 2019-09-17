package com.example.themoviedb.database.repositories

import androidx.paging.LivePagedListBuilder
import com.example.themoviedb.database.cache.moviescategory.PopularMoviesIdListCache
import com.example.themoviedb.database.databaseresults.MoviesResults
import com.example.themoviedb.ui.fragments.popular.inflater.PopularBoundaryCallbacks

class PopularRepository {

    private val DATABASE_PAGE_SIZE = 20

    private val popularMoviesCache = PopularMoviesIdListCache

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