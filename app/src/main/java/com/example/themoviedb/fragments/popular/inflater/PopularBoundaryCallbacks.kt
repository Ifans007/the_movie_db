package com.example.themoviedb.fragments.popular.inflater

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.themoviedb.database.cache.MoviesCache
import com.example.themoviedb.database.entities.MoviesTable
import com.example.themoviedb.retrofitservice.requests.GetRequest

class PopularBoundaryCallbacks : PagedList.BoundaryCallback<MoviesTable>() {

    private var lastRequestedPage = 0


//    private val _popularMoviesList = MutableLiveData<List<Int>>()
//
//    val popularMoviesList: LiveData<List<Int>>
//        get() = _popularMoviesList


    private var _popularMoviesList: MutableList<Int> = mutableListOf()

    val popularMoviesList: List<Int>
        get() = _popularMoviesList


    private val _networkErrors = MutableLiveData<String>()

    val networkErrors: LiveData<String>
        get() = _networkErrors

    private val moviesCache = MoviesCache

    override fun onZeroItemsLoaded() {
        lastRequestedPage = 1
        requestAndSavePopularData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: MoviesTable) {
        lastRequestedPage++
        requestAndSavePopularData()
    }

    private fun requestAndSavePopularData() {

        GetRequest.getPopularMovies(lastRequestedPage,
            { movieRequest ->

                val popularMoviesIdList: MutableList<Int> = mutableListOf()

                for (i in 0 until movieRequest.results!!.size) {
                    val movie =  movieRequest.results!![i]
                    popularMoviesIdList.add(movie.id!!)
                }

                moviesCache.insertMoviesList(
                    popularMoviesIdList,
                    { error -> _networkErrors.postValue(error)},
                    { lastRequestedPage++ }
                )

                _popularMoviesList = popularMoviesIdList

            }, { error ->
                _networkErrors.postValue(error)
            }
        )
    }

}

