package com.example.themoviedb.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "CommonInfoMoviesTable")
class CommonInfoMoviesTable(@PrimaryKey
                            var movieId: Int? = null,
                            var adult: Boolean? = null,
                            var backdropPath: String? = null,
                            var genres: String? = null,
                            var originalLanguage: String? = null,
                            var originalTitle: String? = null,
                            var overview: String? = null,
                            var popularity: Float? = null,
                            var posterPath: String? = null,
                            var releaseDate: String? = null,
                            var title: String? = null,
                            var video: Boolean? = null,
                            var voteAverage: Float? = null,
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