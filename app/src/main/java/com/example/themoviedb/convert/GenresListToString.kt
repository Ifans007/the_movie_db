package com.example.themoviedb.convert

import com.example.themoviedb.database.DatabaseApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

object GenresListToString {

    val databaseApp = DatabaseApp.getInstance()

    @Synchronized fun convert(genreIds: List<Int>?): String {

        var genres = ""

        if (genreIds != null) {

            for (genreId in genreIds) {

                runBlocking {

                    if (genreId != genreIds.last()) {

                        val genre =
                            withContext(Dispatchers.IO) {
                                databaseApp.genreDao().getById(genreId).name
                            }
                        genres += "${genre.toUpperCase()}, "

                    } else {
                        val genre =
                            withContext(Dispatchers.IO) {
                                databaseApp.genreDao().getById(genreId).name
                            }
                        genres += genre.toUpperCase()
                    }
                }
            }


        }

        return genres
    }
}