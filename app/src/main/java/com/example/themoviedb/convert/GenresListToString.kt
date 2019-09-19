package com.example.themoviedb.convert

import com.example.themoviedb.database.DatabaseApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

object GenresListToString {

    val databaseApp = DatabaseApp.getInstance()

    @Synchronized fun convert(genreIds: List<Int>?): String {

        var genres = ""

        if (genreIds != null) {

            for (genreId in genreIds) {

                runBlocking {

                    if (genreId != genreIds.last()) {

                        val genre = async(Dispatchers.IO) { databaseApp.genreDao().getById(genreId).name }
                        genres += "${genre.await().toUpperCase()}, "

                    } else {
                        val genre = async(Dispatchers.IO) { databaseApp.genreDao().getById(genreId).name }
                        genres += genre.await().toUpperCase()
                    }
                }
            }


        }

        return genres
    }
}