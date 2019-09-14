package com.example.themoviedb.database.cache

import androidx.paging.DataSource
import com.example.themoviedb.database.DatabaseApp
import com.example.themoviedb.database.cache.additions.*
import com.example.themoviedb.database.entities.MoviesTable
import com.example.themoviedb.retrofitservice.requests.GetRequest
import com.example.themoviedb.retrofitservice.requests.models.MoviesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object MoviesCache {

    private val databaseMovies = DatabaseApp.getInstance()

    private val belongsToCollectionCache = BelongsToCollectionCache
    private val genreCache               = GenreCache
    private val productionCompanyCache   = ProductionCompanyCache
    private val productionCountryCache   = ProductionCountryCache
    private val spokenLanguageCache      = SpokenLanguageCache

    fun getByIdForDataSource(movieId: Int): DataSource.Factory<Int, MoviesTable> {
        return databaseMovies.moviesDao().getByIdForDataSource()
    }

    fun insertMoviesList(
        listMovies: List<Int>,
        onError: (error: String) -> Unit,
        finished: () -> Unit) = runBlocking(Dispatchers.IO) {

        for (movieId in listMovies) {
            GetRequest.getMovieById(movieId,
                {movie ->

                    val moviesTable = MoviesTable()

                    moviesTable.movieId             = movie.id
                    moviesTable.adult               = movie.adult
                    moviesTable.backdropPath        = movie.backdropPath
                    launch { setBelongsToCollection(movie, moviesTable) }
                    moviesTable.budget              = movie.budget
                    launch { setGenre(movie, moviesTable) }
                    moviesTable.homepage            = movie.homepage
                    moviesTable.imdbId              = movie.imdbId
                    moviesTable.originalLanguage    = movie.originalLanguage
                    moviesTable.originalTitle       = movie.originalTitle
                    moviesTable.overview            = movie.overview
                    moviesTable.popularity          = movie.popularity
                    moviesTable.posterPath          = movie.posterPath
                    launch { setProductionCompanies(movie, moviesTable) }
                    launch { setProductionCountry(movie, moviesTable) }
                    moviesTable.releaseDate         = movie.releaseDate
                    moviesTable.revenue             = movie.revenue
                    moviesTable.runtime             = movie.runtime
                    launch { setSpokenLanguage(movie, moviesTable) }
                    moviesTable.status              = movie.status
                    moviesTable.tagline             = movie.tagline
                    moviesTable.title               = movie.title
                    moviesTable.video               = movie.video
                    moviesTable.voteAverage         = movie.voteAverage
                    moviesTable.voteCount           = movie.voteCount

                    launch { databaseMovies.moviesDao().insert(moviesTable) }
                },

                {error -> onError(error)}
            )
        }
        finished()
    }

    private fun setBelongsToCollection(movie: MoviesModel, moviesTable: MoviesTable) {

        if (movie.belongsToCollection != null) {
            belongsToCollectionCache
                .insert(
                    databaseMovies.belongsToCollectionDao(),
                    movie.belongsToCollection,
                    {belongsToCollectionId -> moviesTable.belongsToCollectionId = belongsToCollectionId}
                )
        }
    }

    private fun setGenre(movie: MoviesModel, moviesTable: MoviesTable) {

        if (movie.genresList != null) {
            genreCache
                .insert(
                    databaseMovies.genreDao(),
                    movie.genresList,
                    {genresIdList
                        ->  moviesTable.genresList = formatString(genresIdList)}
                )
        }
    }

    private fun setProductionCompanies(movie: MoviesModel, moviesTable: MoviesTable) {
        if (movie.productionCompaniesList != null) {
            productionCompanyCache
                .insert(
                    databaseMovies.productionCompanyDao(),
                    movie.productionCompaniesList,
                    {productionCompanyList
                        -> moviesTable.productionCompaniesList = formatString(productionCompanyList)}
                )
        }
    }

    private fun setProductionCountry(movie: MoviesModel, moviesTable: MoviesTable) {
        if (movie.productionCountriesList != null) {
            productionCountryCache
                .insert(
                    databaseMovies.productionCountryDao(),
                    movie.productionCountriesList,
                    {productionCountryCodeList
                        -> moviesTable.productionCountriesList = formatString(productionCountryCodeList)}
                )
        }
    }

    private fun setSpokenLanguage(movie: MoviesModel, moviesTable: MoviesTable) {
        if (movie.spokenLanguagesList != null) {
            spokenLanguageCache
                .insert(
                    databaseMovies.spokenLanguageDao(),
                    movie.spokenLanguagesList,
                    {spokenLanguagesCodeList
                        -> moviesTable.spokenLanguagesList = formatString(spokenLanguagesCodeList)}
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