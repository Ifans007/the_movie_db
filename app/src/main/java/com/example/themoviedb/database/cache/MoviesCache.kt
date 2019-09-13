package com.example.themoviedb.database.cache

import com.example.themoviedb.database.DatabaseApp
import com.example.themoviedb.database.cache.additions.*
import com.example.themoviedb.database.entities.MoviesTable
import com.example.themoviedb.retrofitservice.requests.GetRequest

object MoviesCache {

    private val databaseMovies = DatabaseApp.getInstance()

    private val belongsToCollectionCache = BelongsToCollectionCache
    private val gengeCache               = GenreCache
    private val productionCompanyCache   = ProductionCompanyCache
    private val productionCountryCache   = ProductionCountryCache
    private val spokenLanguageCache      = SpokenLanguageCache

    fun insertMoviesList(
        listMovies: List<Int>,
        onError: (error: String) -> Unit,
        finished: () -> Unit) {

        for (movieId in listMovies.indices) {
            GetRequest.getMovieById(movieId,
                {movie ->

                    val moviesTable = MoviesTable()

                    moviesTable.movieId             = movie.id
                    moviesTable.adult               = movie.adult
                    moviesTable.backdropPath        = movie.backdropPath

                    if (movie.belongsToCollection != null) {
                        belongsToCollectionCache
                            .insert(databaseMovies.belongsToCollectionDao(), movie.belongsToCollection)
                    }

                    moviesTable.belongsToCollection = movie.belongsToCollection

                    moviesTable.budget              = movie.budget
                    moviesTable.genres              = movie.genres
                    moviesTable.homepage            = movie.homepage
                    moviesTable.imdbId              = movie.imdbId
                    moviesTable.originalLanguage    = movie.originalLanguage
                    moviesTable.originalTitle       = movie.originalTitle
                    moviesTable.overview            = movie.overview
                    moviesTable.popularity          = movie.popularity
                    moviesTable.posterPath          = movie.posterPath
                    moviesTable.productionCompanies = movie.productionCompanies
                    moviesTable.productionCountries = movie.productionCountries
                    moviesTable.releaseDate         = movie.releaseDate
                    moviesTable.revenue             = movie.revenue
                    moviesTable.runtime             = movie.runtime
                    moviesTable.spokenLanguages     = movie.spokenLanguages
                    moviesTable.status              = movie.status
                    moviesTable.tagline             = movie.tagline
                    moviesTable.title               = movie.title
                    moviesTable.video               = movie.video
                    moviesTable.voteAverage         = movie.voteAverage
                    moviesTable.voteCount           = movie.voteCount

                    databaseMovies.moviesDao().insert(moviesTable)

                },

                {error -> onError(error)}
            )
        }
        finished()
    }
}