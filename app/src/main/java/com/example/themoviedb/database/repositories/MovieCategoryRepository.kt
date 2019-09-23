package com.example.themoviedb.database.repositories

import androidx.paging.LivePagedListBuilder
import com.example.themoviedb.database.cache.moviescategory.MovieCategoryIdListCache
import com.example.themoviedb.database.databaseresults.MoviesResults
import com.example.themoviedb.ui.main.categories.AbstractFragmentCategory
import com.example.themoviedb.ui.main.categories.inflater.MovieCategoryBoundaryCallbacks

class MovieCategoryRepository(private val abstractFragmentCategory: AbstractFragmentCategory) {

    private val DATABASE_PAGE_SIZE = 20

    private val movieCategoryIdListCache = MovieCategoryIdListCache

    fun movieCategory(): MoviesResults {

        val boundaryCallback = MovieCategoryBoundaryCallbacks(abstractFragmentCategory)

        val dataSourceFactory
                = movieCategoryIdListCache.getAllMovieCategoryId(abstractFragmentCategory.getSimpleName())

        val networkErrors = boundaryCallback.networkErrors

        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()
        return MoviesResults(data, networkErrors)
    }
}