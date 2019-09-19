package com.example.themoviedb.database.cache

import com.example.themoviedb.convert.GenresListToString
import com.example.themoviedb.database.DatabaseApp
import com.example.themoviedb.database.entities.CommonInfoMoviesTable
import com.example.themoviedb.retrofitservice.requests.models.CommonInfoMoviesModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object CommonInfoMoviesCache {

    private val databaseMovies = DatabaseApp.getInstance()

    @Synchronized fun insert(results: List<CommonInfoMoviesModel>) {

        val commonInfoMoviesTableList: MutableList<CommonInfoMoviesTable> = mutableListOf()

        runBlocking {

            for (movie in results) {

                val commonInfoMoviesTable = CommonInfoMoviesTable()

                commonInfoMoviesTable.movieId          = movie.movieId
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

    }

    private fun setGenre(moviesTable: CommonInfoMoviesTable, movie: CommonInfoMoviesModel) {
        moviesTable.genres = GenresListToString.convert(movie.genreIds)
    }

}