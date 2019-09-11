package com.example.themoviedb.database.cache

import androidx.paging.DataSource
import com.example.themoviedb.database.dao.PopularDao
import com.example.themoviedb.database.entities.PopularTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

class PopularCache(private val popularDao: PopularDao) {

    private val ioExecutor = Executors.newSingleThreadExecutor()

    fun insert(list: List<PopularTable>, finished: () -> Unit) {

        ioExecutor.execute {
            popularDao.insert(list)
            finished()
        }
    }

    fun getAllPopular(): DataSource.Factory<Int, PopularTable> {
        return popularDao.loadAllPopular()
    }

    fun getAllItemsInPopular(): Int {
        val data = runBlocking {
            async(Dispatchers.IO) {
                val numItems = popularDao.getNumberOfRows()
                return@async numItems
            }.await()
        }
        return data
    }

}