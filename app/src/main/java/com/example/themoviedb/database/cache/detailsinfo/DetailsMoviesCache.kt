package com.example.themoviedb.database.cache.detailsinfo

import com.example.themoviedb.convert.GenresListToString
import com.example.themoviedb.database.DatabaseApp
import com.example.themoviedb.database.cache.detailsinfo.additions.BelongsToCollectionCache
import com.example.themoviedb.database.cache.detailsinfo.additions.ProductionCompanyCache
import com.example.themoviedb.database.cache.detailsinfo.additions.ProductionCountryCache
import com.example.themoviedb.database.cache.detailsinfo.additions.SpokenLanguageCache
import com.example.themoviedb.database.entities.detailsinfo.DetailsInfoMoviesTable
import com.example.themoviedb.retrofitservice.requests.GetRequest
import com.example.themoviedb.retrofitservice.requests.models.detailsinfo.DetailsInfoMoviesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object DetailsMoviesCache {

    private val databaseMovies = DatabaseApp.getInstance()

    private val belongsToCollectionCache = BelongsToCollectionCache
    private val productionCompanyCache   = ProductionCompanyCache
    private val productionCountryCache   = ProductionCountryCache
    private val spokenLanguageCache      = SpokenLanguageCache

    fun insertMoviesList(
        listMovies: List<Int>,
        onError: (error: String) -> Unit,
        finished: () -> Unit) = runBlocking(Dispatchers.IO) {

        for (movieId in listMovies) {
            GetRequest.getMovieById(movieId,
                {movie ->

                    val moviesTable = DetailsInfoMoviesTable()

                    moviesTable.movieId             = movie.id
                    moviesTable.adult               = movie.adult
                    moviesTable.backdropPath        = movie.backdropPath
                    launch {
                        setBelongsToCollection(
                            moviesTable,
                            movie

                        )
                    }
                    moviesTable.budget              = movie.budget
                    launch {
                        setGenre(
                            moviesTable,
                            movie
                        )
                    }
                    moviesTable.homepage            = movie.homepage
                    moviesTable.imdbId              = movie.imdbId
                    moviesTable.originalLanguage    = movie.originalLanguage
                    moviesTable.originalTitle       = movie.originalTitle
                    moviesTable.overview            = movie.overview
                    moviesTable.popularity          = movie.popularity
                    moviesTable.posterPath          = movie.posterPath
                    launch {
                        setProductionCompanies(
                            moviesTable,
                            movie
                        )
                    }
                    launch {
                        setProductionCountry(
                            moviesTable,
                            movie
                        )
                    }
                    moviesTable.releaseDate         = movie.releaseDate
                    moviesTable.revenue             = movie.revenue
                    moviesTable.runtime             = movie.runtime
                    launch {
                        setSpokenLanguage(
                            moviesTable,
                            movie
                        )
                    }
                    moviesTable.status              = movie.status
                    moviesTable.tagline             = movie.tagline
                    moviesTable.title               = movie.title
                    moviesTable.video               = movie.video
                    moviesTable.voteAverage         = movie.voteAverage
                    moviesTable.voteCount           = movie.voteCount

                    launch { databaseMovies.detailsInfoMoviesDao().insert(moviesTable) }
                },

                {error -> onError(error)}
            )
        }
        finished()
    }

    private fun setBelongsToCollection(
        moviesTable: DetailsInfoMoviesTable,
        movie: DetailsInfoMoviesModel
    ) {

        if (movie.belongsToCollection != null) {
            belongsToCollectionCache
                .insert(
                    databaseMovies.belongsToCollectionDao(),
                    movie.belongsToCollection,
                    {belongsToCollectionId -> moviesTable.belongsToCollectionId = belongsToCollectionId}
                )
        }
    }

    private fun setGenre(
        moviesTable: DetailsInfoMoviesTable,
        movie: DetailsInfoMoviesModel
    ) {

        val genres: MutableList<Int> = mutableListOf()

        for (genreId in movie.genresList!!.indices) {

            genres.add(genreId)
        }

        moviesTable.genresList = GenresListToString.convert(genres)
    }

    private fun setProductionCompanies(
        moviesTable: DetailsInfoMoviesTable,
        movie: DetailsInfoMoviesModel
    ) {
        if (movie.productionCompaniesList != null) {
            productionCompanyCache
                .insert(
                    databaseMovies.productionCompanyDao(),
                    movie.productionCompaniesList,
                    {productionCompanyList
                        -> moviesTable.productionCompaniesList =
                        formatString(
                            productionCompanyList
                        )
                    }
                )
        }
    }

    private fun setProductionCountry(
        moviesTable: DetailsInfoMoviesTable,
        movie: DetailsInfoMoviesModel
    ) {
        if (movie.productionCountriesList != null) {
            productionCountryCache
                .insert(
                    databaseMovies.productionCountryDao(),
                    movie.productionCountriesList,
                    {productionCountryCodeList
                        -> moviesTable.productionCountriesList =
                        formatString(
                            productionCountryCodeList
                        )
                    }
                )
        }
    }

    private fun setSpokenLanguage(
        moviesTable: DetailsInfoMoviesTable,
        movie: DetailsInfoMoviesModel
    ) {
        if (movie.spokenLanguagesList != null) {
            spokenLanguageCache
                .insert(
                    databaseMovies.spokenLanguageDao(),
                    movie.spokenLanguagesList,
                    {spokenLanguagesCodeList
                        -> moviesTable.spokenLanguagesList =
                        formatString(
                            spokenLanguagesCodeList
                        )
                    }
                )
        }
    }

    private fun formatString(list: String): String {
        return list
            .replace("[", "")
            .replace(",", "")
            .replace("]", "")
    }

}