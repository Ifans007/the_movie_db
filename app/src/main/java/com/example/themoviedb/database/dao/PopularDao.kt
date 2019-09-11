package com.example.themoviedb.database.dao

import androidx.paging.DataSource
import androidx.room.*

import com.example.themoviedb.database.entities.PopularTable

@Dao
interface PopularDao {

    @Query("SELECT * FROM popular ORDER BY popularity DESC, voteCount Desc")
    fun loadAllPopular(): DataSource.Factory<Int, PopularTable>

    @Query("SELECT * FROM popular WHERE movieId = :id ORDER BY timeAdded")
    fun checkIfPopular(id: Int):Boolean

    @Insert
    fun insertPopular(popularEntry: PopularTable)

    @Delete
    fun deletePopular(popularEntry: PopularTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(searches: List<PopularTable>)

    @Query("DELETE FROM popular")
    fun deleteAll()

    @Query("SELECT COUNT(movieId) FROM popular")
    fun getNumberOfRows(): Int
}