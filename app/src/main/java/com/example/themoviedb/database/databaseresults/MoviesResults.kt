package com.example.themoviedb.database.databaseresults

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.themoviedb.database.entities.MoviesTable

data class MoviesResults(
    val data: LiveData<PagedList<MoviesTable>>,
    val networkErrors: LiveData<String>
)