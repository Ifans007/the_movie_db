package com.example.themoviedb.database.cache.detailsinfo.additions

import com.example.themoviedb.database.dao.detailsinfo.additions.GenreDao
import com.example.themoviedb.database.entities.detailsinfo.additions.GenreTable
import com.example.themoviedb.retrofitservice.requests.models.detailsinfo.additions.GenreModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object GenreCache {

    fun insert(
        genreDao: GenreDao,
        genres: List<GenreModel>
    ) {

        val genresList: MutableList<GenreTable> = mutableListOf()

        for (genre in genres) {

            val genreTable = GenreTable()

            genreTable.id   = genre.id
            genreTable.name = genre.name

            genresList.add(genreTable)
        }

        runBlocking {
            launch(Dispatchers.IO) {
                genreDao.insert(genresList)
            }.join()
        }


    }

}