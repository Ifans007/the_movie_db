package com.example.themoviedb.database.datasource

import androidx.lifecycle.LiveData
import androidx.paging.PositionalDataSource
import com.example.themoviedb.database.entities.MoviesTable

class MovieDataSource(dataSourceFactory: LiveData<List<Int>>) : PositionalDataSource<MoviesTable>() {

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<MoviesTable>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<MoviesTable>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}