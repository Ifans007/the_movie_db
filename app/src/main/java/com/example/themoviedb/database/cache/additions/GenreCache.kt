package com.example.themoviedb.database.cache.additions

import com.example.themoviedb.database.dao.additions.GenreDao
import com.example.themoviedb.database.entities.additions.GenreTable
import com.example.themoviedb.retrofitservice.requests.models.Genre

object GenreCache {

    fun insert(
        genreDao: GenreDao,
        genres: List<Genre>,
        returnGenresIdList: (genresIdList: String) -> Unit
    ) {

        val genresIdList: MutableList<Int> = mutableListOf()

        for (genre in genres) {

            val genreTable = GenreTable()

            genreTable.id   = genre.id
            genreTable.name = genre.name

            genreDao.insert(genreTable)

            genresIdList.add(genre.id)
        }

        returnGenresIdList(genresIdList.toString())
    }
}