package com.example.themoviedb.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.themoviedb.database.entities.additions.*


@Entity(tableName = "MoviesTable")
class MoviesTable(@PrimaryKey
                  var movieId: Int? = null,
                  var adult: Boolean? = null,
                  var backdropPath: String? = null,
                  var belongsToCollection: BelongsToCollectionTable? = null,
                  var budget: Int? = null,
                  var genres: List<GenreTable>? = null,
                  var homepage: String? = null,
                  var imdbId: String? = null,
                  var originalLanguage: String? = null,
                  var originalTitle: String? = null,
                  var overview: String? = null,
                  var popularity: Double? = null,
                  var posterPath: String? = null,
                  var productionCompanies: List<ProductionCompanyTable>? = null,
                  var productionCountries: List<ProductionCountryTable>? = null,
                  var releaseDate: String? = null,
                  var revenue: Int? = null,
                  var runtime: Int? = null,
                  var spokenLanguages: List<SpokenLanguageTable>? = null,
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