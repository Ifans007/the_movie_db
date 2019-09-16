package com.example.themoviedb.database.databaseresults

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.themoviedb.database.entities.moviescategory.PopularMoviesIdTable

data class MoviesResults(
    val data: LiveData<PagedList<PopularMoviesIdTable>>,
    val networkErrors: LiveData<String>
)