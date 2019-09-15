package com.example.themoviedb.database.dao.moviescategory

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themoviedb.database.entities.moviescategory.PopularMoviesTable

@Dao
interface PopularMoviesDao {

    @Query("SELECT * FROM PopularMoviesTable")
    fun getPopularMovies(): DataSource.Factory<Int, PopularMoviesTable>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movie: PopularMoviesTable)

}