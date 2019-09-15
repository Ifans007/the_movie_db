package com.example.themoviedb.database.databaseresults

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.themoviedb.database.entities.moviescategory.PopularMoviesTable

data class MoviesResults(
    val data: LiveData<PagedList<PopularMoviesTable>>,
    val networkErrors: LiveData<String>
)