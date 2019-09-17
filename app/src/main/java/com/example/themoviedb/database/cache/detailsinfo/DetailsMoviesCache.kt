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
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object DetailsMoviesCache {

    private val databaseMovies = DatabaseApp.getInstance()

    private val belongsToCollectionCache = BelongsToCollectionCache
    private val productionCompanyCache   = ProductionCompanyCache
    private val productionCountryCache   = ProductionCountryCache
    private val spokenLanguageCache      = SpokenLanguageCache



    fun insert(
        movieId: Int,
        onSuccess: (movie: DetailsInfoMoviesTable) -> Unit,
        onError: (error: String) -> Unit) = runBlocking {

        val movieRequest = async(Dispatchers.IO) { GetRequest.getDetailsMovieById(movieId)}.await()

        if (movieRequest != null) {
            val movie = cacheMovie(movieRequest)
            onSuccess(movie)
        } else {
            onError("Network error")
        }
    }

    private fun cacheMovie(result: DetailsInfoMoviesModel)

            = runBlocking(Dispatchers.IO) {

                val moviesTable = DetailsInfoMoviesTable()

                launch(Dispatchers.IO) {

                    moviesTable.movieId = result.id
                    moviesTable.adult = result.adult
                    moviesTable.backdropPath = result.backdropPath
                    launch {
                        setBelongsToCollection(
                            moviesTable,
                            result
                        )
                    }
                    moviesTable.budget = result.budget
                    launch {
                        setGenre(
                            moviesTable,
                            result
                        )
                    }
                    moviesTable.homepage = result.homepage
                    moviesTable.imdbId = result.imdbId
                    moviesTable.originalLanguage = result.originalLanguage
                    moviesTable.originalTitle = result.originalTitle
                    moviesTable.overview = result.overview
                    moviesTable.popularity = result.popularity
                    moviesTable.posterPath = result.posterPath
                    launch {
                        setProductionCompanies(
                            moviesTable,
                            result
                        )
                    }
                    launch {
                        setProductionCountry(
                            moviesTable,
                            result
                        )
                    }
                    moviesTable.releaseDate = result.releaseDate
                    moviesTable.revenue = result.revenue
                    moviesTable.runtime = result.runtime
                    launch {
                        setSpokenLanguage(
                            moviesTable,
                            result
                        )
                    }
                    moviesTable.status = result.status
                    moviesTable.tagline = result.tagline
                    moviesTable.title = result.title
                    moviesTable.video = result.video
                    moviesTable.voteAverage = result.voteAverage
                    moviesTable.voteCount = result.voteCount

                }.join()

                launch(Dispatchers.IO) { databaseMovies.detailsInfoMoviesDao().insert(moviesTable) }.join()

                return@runBlocking moviesTable
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

        for (genreId in movie.genresList!!) {

            genres.add(genreId.id!!)
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