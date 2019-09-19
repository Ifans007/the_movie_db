package com.example.themoviedb.database.entities.detailsinfo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DetailsInfoMoviesTable")
class DetailsInfoMoviesTable {

    @PrimaryKey
    var movieId: Int = -1
    var adult: Boolean = false
    lateinit var backdropPath: String
    var belongsToCollectionId: Int = -1
    var budget: Int = -1
    lateinit var genresList: String
    lateinit var homepage: String
    lateinit var imdbId: String
    lateinit var originalLanguage: String
    lateinit var originalTitle: String
    lateinit var overview: String
    var popularity: Float = -1f
    lateinit var posterPath: String
    lateinit var productionCompaniesList: String
    lateinit var productionCountriesList: String
    lateinit var releaseDate: String
    var revenue: Long = -1
    var runtime: Int = -1
    lateinit var spokenLanguagesList: String
    lateinit var status: String
    lateinit var tagline: String
    lateinit var title: String
    var video: Boolean = false
    var voteAverage: Float = -1f
    var voteCount: Int = -1

    override fun equals(other: Any?): Boolean {
        return movieId == other
    }

    override fun hashCode(): Int {
        return movieId
    }

    override fun toString(): String {
        return "Movie(id=$movieId, title=$title)"
    }
}