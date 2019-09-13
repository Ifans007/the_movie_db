package com.example.themoviedb.database.dao.additions

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themoviedb.database.entities.additions.BelongsToCollectionTable

@Dao
interface BelongsToCollectionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(insert: BelongsToCollectionTable)

    @Query("SELECT * FROM BelongsToCollectionTable WHERE id = :id")
    fun getById(id: Int): BelongsToCollectionTable
}