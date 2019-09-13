package com.example.themoviedb.database.dao.additions

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themoviedb.database.entities.additions.GenreTable

@Dao
interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(insert: GenreTable)

    @Query("SELECT * FROM GenreTable WHERE id = :id")
    fun getById(id: Int): GenreTable
}