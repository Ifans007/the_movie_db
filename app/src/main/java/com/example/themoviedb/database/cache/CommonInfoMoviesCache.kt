package com.example.themoviedb.database.cache

import com.example.themoviedb.convert.GenresListToString
import com.example.themoviedb.database.DatabaseApp
import com.example.themoviedb.database.entities.CommonInfoMoviesTable
import com.example.themoviedb.retrofitservice.requests.models.CommonInfoMoviesModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

object CommonInfoMoviesCache {

    private val databaseMovies = DatabaseApp.getInstance()

    @Synchronized suspend fun insert(results: List<CommonInfoMoviesModel>): List<Int> {

        val commonInfoMoviesTableList = mutableListOf<CommonInfoMoviesTable>()
        val listMoviesId = mutableListOf<Int>()


        coroutineScope {

            for (movie in results) {

                val commonInfoMoviesTable = CommonInfoMoviesTable()

                commonInfoMoviesTable.movieId          = movie.movieId
                listMoviesId.add(movie.movieId)
                commonInfoMoviesTable.adult            = movie.adult
                commonInfoMoviesTable.backdropPath     = movie.backdropPath ?: ""
                val genreCoroutine = launch { setGenre(commonInfoMoviesTable, movie) }
                commonInfoMoviesTable.originalLanguage = movie.originalLanguage
                commonInfoMoviesTable.originalTitle    = movie.originalTitle
                commonInfoMoviesTable.overview         = movie.overview
                commonInfoMoviesTable.popularity       = movie.popularity
                commonInfoMoviesTable.posterPath       = movie.posterPath ?: ""
                commonInfoMoviesTable.releaseDate      = movie.releaseDate
                commonInfoMoviesTable.title            = movie.title
                commonInfoMoviesTable.video            = movie.video
                commonInfoMoviesTable.voteAverage      = movie.voteAverage
                commonInfoMoviesTable.voteCount        = movie.voteCount

                genreCoroutine.join()

                commonInfoMoviesTableList.add(commonInfoMoviesTable)
            }

            launch { databaseMovies.commonInfoMoviesDao().insert(commonInfoMoviesTableList) }.join()
        }
        return listMoviesId
    }

    private fun setGenre(moviesTable: CommonInfoMoviesTable, movie: CommonInfoMoviesModel) {
        moviesTable.genres = GenresListToString.convert(movie.genreIds)
    }

}