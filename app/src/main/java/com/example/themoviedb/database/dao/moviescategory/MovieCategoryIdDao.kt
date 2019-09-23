package com.example.themoviedb.database.dao.moviescategory

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themoviedb.database.entities.moviescategory.MovieCategoryIdTable

@Dao
interface MovieCategoryIdDao {

    @Query("SELECT * FROM MovieCategoryIdTable WHERE category = :movieCategory ORDER BY counter ASC")
    fun getAllMovieCategoryId(movieCategory: String): DataSource.Factory<Int, MovieCategoryIdTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies: List<MovieCategoryIdTable>)


}