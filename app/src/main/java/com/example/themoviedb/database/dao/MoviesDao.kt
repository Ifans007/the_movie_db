package com.example.themoviedb.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themoviedb.database.entities.MoviesTable

@Dao
interface MoviesDao {

//    @Query("SELECT * FROM MoviesTable ORDER BY popularity DESC, voteCount DESC")
//    fun getByIdForDataSource(): DataSource.Factory<Int, MoviesTable>

//    @Query("SELECT * FROM MoviesTable WHERE movieId = :movieId")
//    fun getByIdForDataSource(movieId: Int): DataSource.Factory<Int, MoviesTable>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movie: MoviesTable)

    @Query("SELECT * FROM MoviesTable WHERE movieId = :movieId")
    fun getById(movieId: Int): MoviesTable
}