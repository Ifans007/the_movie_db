package com.example.themoviedb.database.dao.detailsinfo.additions

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themoviedb.database.entities.detailsinfo.additions.SpokenLanguageTable

@Dao
interface SpokenLanguageDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(insert: SpokenLanguageTable)

    @Query("SELECT * FROM SpokenLanguageTable WHERE code = :code")
    fun getById(code: Int): SpokenLanguageTable
}