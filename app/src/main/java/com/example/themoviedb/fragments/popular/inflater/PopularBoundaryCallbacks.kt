package com.example.themoviedb.fragments.popular.inflater

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.themoviedb.database.cache.CommonInfoMoviesCache
import com.example.themoviedb.database.cache.moviescategory.PopularMoviesIdListCache
import com.example.themoviedb.database.entities.moviescategory.PopularMoviesIdTable
import com.example.themoviedb.retrofitservice.requests.GetRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class PopularBoundaryCallbacks : PagedList.BoundaryCallback<PopularMoviesIdTable>() {

    private var lastRequestedPage = 0

    private val _networkErrors = MutableLiveData<String>()

    val networkErrors: LiveData<String>
        get() = _networkErrors

    private val popularMoviesIdListCache = PopularMoviesIdListCache

    private val commonInfoMoviesCache = CommonInfoMoviesCache

    override fun onZeroItemsLoaded() {
        lastRequestedPage = 1
        requestAndSavePopularData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: PopularMoviesIdTable) {
        lastRequestedPage++
        requestAndSavePopularData()
    }

    private fun requestAndSavePopularData() {

        GetRequest.getPopularMovies(lastRequestedPage,
            { movieRequest ->

                runBlocking {

                    launch(Dispatchers.IO) { commonInfoMoviesCache.insert(movieRequest.results!!) }.join()

                    popularMoviesIdListCache.insert(movieRequest.results!!)
                }

            }, { error ->
                _networkErrors.postValue(error)
            }
        )
    }

}

