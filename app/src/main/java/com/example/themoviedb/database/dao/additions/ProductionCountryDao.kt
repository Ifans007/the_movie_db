package com.example.themoviedb.database.dao.additions

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themoviedb.database.entities.additions.ProductionCountryTable

@Dao
interface ProductionCountryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(insert: ProductionCountryTable)

    @Query("SELECT * FROM ProductionCountryTable WHERE code = :code")
    fun getById(code: Int): ProductionCountryTable
}