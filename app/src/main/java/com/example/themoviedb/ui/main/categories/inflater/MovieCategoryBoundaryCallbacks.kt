package com.example.themoviedb.ui.main.categories.inflater

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.themoviedb.database.cache.CommonInfoMoviesCache
import com.example.themoviedb.database.cache.moviescategory.MovieCategoryIdListCache
import com.example.themoviedb.database.entities.moviescategory.MovieCategoryIdTable
import com.example.themoviedb.ui.main.categories.AbstractFragmentCategory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieCategoryBoundaryCallbacks(
    private val abstractFragmentCategory: AbstractFragmentCategory) : PagedList.BoundaryCallback<MovieCategoryIdTable>() {

    private var lastRequestedPage = 0

    private val _networkErrors = MutableLiveData<String>()

    val networkErrors: LiveData<String>
        get() = _networkErrors

    private val movieCategoryIdListCache = MovieCategoryIdListCache

    private val commonInfoMoviesCache = CommonInfoMoviesCache

    override fun onZeroItemsLoaded() {
        requestAndSaveData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: MovieCategoryIdTable) {
        requestAndSaveData()
    }

    private fun requestAndSaveData() {

        lastRequestedPage++

        abstractFragmentCategory.getRequest(lastRequestedPage,
            { movieRequest ->

                CoroutineScope(Dispatchers.IO).launch {

                    val listMoviesId = withContext(Dispatchers.IO) {
                        commonInfoMoviesCache.insert(movieRequest.results!!)
                    }

                    movieCategoryIdListCache.insert(listMoviesId, abstractFragmentCategory.getSimpleName())
                }

            }, { error ->
                _networkErrors.postValue(error)
            }
        )
    }

}

