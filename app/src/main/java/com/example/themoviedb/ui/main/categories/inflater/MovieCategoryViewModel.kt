package com.example.themoviedb.ui.main.categories.inflater

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.themoviedb.database.databaseresults.MoviesResults
import com.example.themoviedb.database.entities.moviescategory.MovieCategoryIdTable
import com.example.themoviedb.database.repositories.MovieCategoryRepository

class MovieCategoryViewModel(private val repository: MovieCategoryRepository) : ViewModel() {

    private val queryLiveData = MutableLiveData<String>()
    private val movieCategoryResult: LiveData<MoviesResults> = Transformations.map(queryLiveData, { repository.movieCategory() })

    val nowShowing: LiveData<PagedList<MovieCategoryIdTable>> = Transformations.switchMap(movieCategoryResult) { it.data }
    val networkErrors: LiveData<String> = Transformations.switchMap(movieCategoryResult) { it.networkErrors }

    fun getPopular(region: String) {
        queryLiveData.value = region
    }
}