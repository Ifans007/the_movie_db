package com.example.themoviedb.database.databaseresults

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.themoviedb.database.entities.moviescategory.MovieCategoryIdTable

data class MoviesResults(
    val data: LiveData<PagedList<MovieCategoryIdTable>>,
    val networkErrors: LiveData<String>
)