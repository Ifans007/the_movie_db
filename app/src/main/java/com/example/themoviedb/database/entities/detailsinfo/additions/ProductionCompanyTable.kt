package com.example.themoviedb.database.entities.detailsinfo.additions

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ProductionCompanyTable")
class ProductionCompanyTable {

    @PrimaryKey
    var id: Int = -1
    lateinit var logoPath: String
    lateinit var name: String
    lateinit var originCountry: String



    override fun equals(other: Any?): Boolean {
        return id == other
    }

    override fun hashCode(): Int {
        return id
    }

    override fun toString(): String {
        return "ProductionCompanyDao (id: $id, name: $name)"
    }
}
