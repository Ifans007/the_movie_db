package com.example.themoviedb.database.entities.moviescategory

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PopularMoviesTable")
data class PopularMoviesTable(@PrimaryKey
                         var movieId: Int? = null) {
}