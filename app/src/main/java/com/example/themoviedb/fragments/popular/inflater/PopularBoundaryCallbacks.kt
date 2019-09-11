package com.example.themoviedb.fragments.popular.inflater

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.themoviedb.database.cache.PopularCache
import com.example.themoviedb.database.entities.PopularTable
import com.example.themoviedb.retrofitservice.requests.models.GetRequest
import java.util.*

class PopularBoundaryCallbacks(private val popularCache: PopularCache) : PagedList.BoundaryCallback<PopularTable>() {

    private var lastRequestedPage = (popularCache.getAllItemsInPopular() / 2) + 1

    private val _networkErrors = MutableLiveData<String>()

    val networkErrors: LiveData<String>
        get() = _networkErrors

    private var isRequestInProgress = false

    override fun onZeroItemsLoaded() {
        requestAndSavePopularData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: PopularTable) {
        requestAndSavePopularData()
    }

    private fun requestAndSavePopularData() {
        if (isRequestInProgress) return

        isRequestInProgress = true

        GetRequest.getPopularMovies(lastRequestedPage,
            { movierequest ->
                val popularTableList: MutableList<PopularTable> = mutableListOf()
                for (i in 0 until movierequest.results!!.size){
                    val popularTable = PopularTable()
                    val movie =  movierequest.results!![i]
                    popularTable.movieId = movie.id
                    popularTable.voteCount = movie.voteCount
                    popularTable.video = movie.video
                    popularTable.voteAverage = movie.voteAverage
                    popularTable.title = movie.title
                    popularTable.popularity = movie.popularity
                    popularTable.posterPath = movie.posterPath
                    popularTable.originalLanguage = movie.originalLanguage
                    popularTable.originalTitle = movie.originalTitle
                    popularTable.backdropPath = movie.backdropPath
                    popularTable.adult = movie.adult
                    popularTable.overview = movie.overview
                    popularTable.releaseDate = movie.releaseDate
                    for (j in 0 until movie.genreIds!!.size) {
                        if(j == movie.genreIds!!.size-1)
                            movie.genreString += getGenre(
                                movie.genreIds!!.get(j)
                            )
                        else
                            movie.genreString += getGenre(
                                movie.genreIds!!.get(j)
                            ) +", "
                    }
                    popularTable.genreString = movie.genreString
                    popularTable.contentType = CONTENT_SIMILAR
                    popularTable.timeAdded = Date().time

                    if (popularTable.backdropPath.isNullOrEmpty()) popularTable.backdropPath = RANDOM_PATH
                    if (popularTable.posterPath.isNullOrEmpty()) popularTable.posterPath = RANDOM_PATH
                    popularTableList.add(popularTable)
                }
                popularCache.insert(popularTableList,{
                    lastRequestedPage++
                    isRequestInProgress = false
                })
            }, { error ->
                _networkErrors.postValue(error)
                isRequestInProgress = false
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
            genreMap.put(10770, "TV Movie")
            genreMap.put(53, "Thriller")
            genreMap.put(10752, "War")
            genreMap.put(37, "Western")

            return genreMap.get(id)!!
        }
    }

    val CONTENT_SIMILAR = 3
    val RANDOM_PATH = "qwerty"

}