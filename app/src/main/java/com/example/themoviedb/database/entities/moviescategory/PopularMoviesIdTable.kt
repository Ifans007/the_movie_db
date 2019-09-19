package com.example.themoviedb.database.entities.moviescategory

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PopularMoviesIdTable")

data class PopularMoviesIdTable(@PrimaryKey
                                var counter: Int = 0,
                                var movieId: Int = -1
)