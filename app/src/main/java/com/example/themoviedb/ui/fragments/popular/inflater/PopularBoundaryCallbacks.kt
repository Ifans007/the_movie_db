package com.example.themoviedb.ui.fragments.popular.inflater

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.themoviedb.database.cache.CommonInfoMoviesCache
import com.example.themoviedb.database.cache.moviescategory.PopularMoviesIdListCache
import com.example.themoviedb.database.entities.moviescategory.PopularMoviesIdTable
import com.example.themoviedb.retrofitservice.requests.GetRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PopularBoundaryCallbacks : PagedList.BoundaryCallback<PopularMoviesIdTable>() {

    private var lastRequestedPage = 0

    private val _networkErrors = MutableLiveData<String>()

    val networkErrors: LiveData<String>
        get() = _networkErrors

    private val popularMoviesIdListCache = PopularMoviesIdListCache

    private val commonInfoMoviesCache = CommonInfoMoviesCache

    override fun onZeroItemsLoaded() {
        requestAndSavePopularData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: PopularMoviesIdTable) {
        requestAndSavePopularData()
    }

    private fun requestAndSavePopularData() {

        lastRequestedPage++

        GetRequest.getPopularMovies(lastRequestedPage,
            { movieRequest ->

                CoroutineScope(Dispatchers.IO).launch {

                    val listMoviesId = withContext(Dispatchers.IO) {
                            commonInfoMoviesCache.insert(movieRequest.results!!)
                    }

                    popularMoviesIdListCache.insert(listMoviesId)
                }

            }, { error ->
                _networkErrors.postValue(error)
            }
        )
    }

}

