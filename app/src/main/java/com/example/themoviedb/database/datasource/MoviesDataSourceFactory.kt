package com.example.themoviedb.database.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PositionalDataSource

class MoviesDataSourceFactory(var movies: List<Int>) : DataSource.Factory<Int, Int>() {

    val sourceLiveData = MutableLiveData<MoviesDataSource>()

    override fun create(): DataSource<Int, Int> {
        val source = MoviesDataSource(movies)
        sourceLiveData.postValue(source)
        return source
    }




    class MoviesDataSource(var movies: List<Int>) : PositionalDataSource<Int>() {

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Int>) {
            val totalCount = movies.size

            val position = computeInitialLoadPosition(params, totalCount)
            val loadSize = computeInitialLoadSize(params, position, totalCount)

            val sublist = movies.subList(position, position + loadSize)

            callback.onResult(sublist, position, totalCount)
        }


        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Int>) {
            callback.onResult(movies.subList(params.startPosition, params.startPosition + params.loadSize))
        }



    }
}