package com.example.themoviedb.database.databaseresults

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.themoviedb.database.entities.PopularTable

data class PopularResults(
    val data: LiveData<PagedList<PopularTable>>,
    val networkErrors: LiveData<String>
)