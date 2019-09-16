package com.example.themoviedb.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themoviedb.database.entities.CommonInfoMoviesTable

@Dao
interface CommonInfoMoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movies: List<CommonInfoMoviesTable>)

    @Query("SELECT * FROM CommonInfoMoviesTable WHERE movieId = :movieId")
    fun getById(movieId: Int): CommonInfoMoviesTable
}