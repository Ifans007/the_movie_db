package com.example.themoviedb.database.dao.detailsinfo.additions

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.themoviedb.database.entities.detailsinfo.additions.ProductionCompanyTable

@Dao
interface ProductionCompanyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(insert: ProductionCompanyTable)

    @Query("SELECT * FROM ProductionCompanyTable WHERE id = :id")
    fun getById(id: Int): ProductionCompanyTable
}