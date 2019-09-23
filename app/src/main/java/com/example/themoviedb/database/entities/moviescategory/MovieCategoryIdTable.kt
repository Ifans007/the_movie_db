package com.example.themoviedb.database.entities.moviescategory

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MovieCategoryIdTable")

data class MovieCategoryIdTable(@PrimaryKey
                                var counter: Int = 0,
                                var movieId: Int = -1,
                                var category: String = ""
)