package com.example.themoviedb.fragments.popular.inflater

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.themoviedb.database.cache.MoviesCache
import com.example.themoviedb.database.entities.MoviesTable
import com.example.themoviedb.retrofitservice.requests.GetRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*

class PopularBoundaryCallbacks : PagedList.BoundaryCallback<MoviesTable>() {

    private var lastRequestedPage = 0

    private val _networkErrors = MutableLiveData<String>()

    val networkErrors: LiveData<String>
        get() = _networkErrors

    private var isRequestInProgress = false

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
        runBlocking {
            launch(Dispatchers.IO) {
                startRequest()
            }
        }
    }

    private suspend fun startRequest() {
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
                    { lastRequestedPage++
                    }
                )

            }, { error ->
                _networkErrors.postValue(error)
            })
    }

    companion object {
        fun getGenre(id: Int): String {
            val genreMap = HashMap<Int, String>()
            genreMap.put(28, "Action")
            genreMap.put(12, "Adventure")
            genreMap.put(16, "Animation")
            genreMap.put(35, "Comedy")
            genreMap.put(80, "Crime")
            genreMap.put(99, "Documentary")
            genreMap.put(18, "Drama")
            genreMap.put(10751, "Family")
            genreMap.put(14, "Fantasy")
            genreMap.put(36, "History")
            genreMap.put(27, "Horror")
            genreMap.put(10402, "Music")
            genreMap.put(9648, "Mystery")
            genreMap.put(10749, "Romance")
            genreMap.put(878, "Science Fiction")
            genreMap.put(10770, "TV PopularMoviesModel")
            genreMap.put(53, "Thriller")
            genreMap.put(10752, "War")
            genreMap.put(37, "Western")

            return genreMap.get(id)!!
        }
    }

    val CONTENT_SIMILAR = 3
    val RANDOM_PATH = "qwerty"

}