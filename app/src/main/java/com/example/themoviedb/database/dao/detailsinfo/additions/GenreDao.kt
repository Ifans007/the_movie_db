package com.example.themoviedb.database.dao.detailsinfo.additions

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themoviedb.database.entities.detailsinfo.additions.GenreTable

@Dao
interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(insert: List<GenreTable>)

    @Query("SELECT * FROM GenreTable WHERE id = :id")
    fun getById(id: Int): GenreTable

    @Query("SELECT * FROM GenreTable")
    fun getAll(): List<GenreTable>
}