package com.example.themoviedb.database.dao.detailsinfo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themoviedb.database.entities.detailsinfo.DetailsInfoMoviesTable

@Dao
interface DetailsInfoMoviesDao {

    @Query("SELECT * FROM DetailsInfoMoviesTable")
    fun getAll(): List<DetailsInfoMoviesTable>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movie: DetailsInfoMoviesTable)

    @Query("SELECT * FROM DetailsInfoMoviesTable WHERE movieId = :movieId")
    fun getById(movieId: Int): DetailsInfoMoviesTable?
}