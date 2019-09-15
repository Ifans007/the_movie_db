package com.example.themoviedb.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "MoviesTable")
class MoviesTable(@PrimaryKey
                  var movieId: Int? = null,
                  var adult: Boolean? = null,
                  var backdropPath: String? = null,
                  var belongsToCollectionId: Int? = null,
                  var budget: Int? = null,
                  var genresList: String? = null,
                  var homepage: String? = null,
                  var imdbId: String? = null,
                  var originalLanguage: String? = null,
                  var originalTitle: String? = null,
                  var overview: String? = null,
                  var popularity: Double? = null,
                  var posterPath: String? = null,
                  var productionCompaniesList: String? = null,
                  var productionCountriesList: String? = null,
                  var releaseDate: String? = null,
                  var revenue: Long? = null,
                  var runtime: Int? = null,
                  var spokenLanguagesList: String? = null,
                  var status: String? = null,
                  var tagline: String? = null,
                  var title: String? = null,
                  var video: Boolean? = null,
                  var voteAverage: Double? = null,
                  var voteCount: Int? = null)
{

    override fun equals(other: Any?): Boolean {
        return movieId == other
    }

    override fun hashCode(): Int {
        return movieId!!
    }

    override fun toString(): String {
        return "Movie(id=$movieId, title=$title)"
    }
}