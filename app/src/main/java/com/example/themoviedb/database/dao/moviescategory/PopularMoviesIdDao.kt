package com.example.themoviedb.database.dao.moviescategory

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themoviedb.database.entities.moviescategory.PopularMoviesIdTable

@Dao
interface PopularMoviesIdDao {

    @Query("SELECT * FROM PopularMoviesIdTable ORDER BY counter ASC")
    fun getPopularMovies(): DataSource.Factory<Int, PopularMoviesIdTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies: List<PopularMoviesIdTable>)


}