package com.example.themoviedb.fragments.popular.inflater

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.themoviedb.database.databaseresults.MoviesResults
import com.example.themoviedb.database.entities.MoviesTable
import com.example.themoviedb.database.repositories.PopularRepository

class PopularViewModel(private val repository: PopularRepository) : ViewModel() {

    private val queryLiveData = MutableLiveData<String>()
    private val popularResult: LiveData<MoviesResults> = Transformations.map(queryLiveData, { repository.popular() })

    val nowShowing: LiveData<PagedList<MoviesTable>> = Transformations.switchMap(popularResult) { it.data }
    val networkErrors: LiveData<String> = Transformations.switchMap(popularResult) { it.networkErrors }

    fun getPopular(region: String) {
        queryLiveData.value = region
    }
}