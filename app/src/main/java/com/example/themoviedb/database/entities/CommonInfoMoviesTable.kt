package com.example.themoviedb.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "CommonInfoMoviesTable")
class CommonInfoMoviesTable
{

    @PrimaryKey
    var movieId: Int = -1
    var adult: Boolean = false
    lateinit var backdropPath: String
    lateinit var genres: String
    lateinit var originalLanguage: String
    lateinit var originalTitle: String
    lateinit var overview: String
    var popularity: Float = -1f
    lateinit var posterPath: String
    lateinit var releaseDate: String
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