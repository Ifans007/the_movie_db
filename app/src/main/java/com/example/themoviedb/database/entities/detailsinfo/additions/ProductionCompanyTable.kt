package com.example.themoviedb.database.entities.detailsinfo.additions

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ProductionCompanyTable")
class ProductionCompanyTable(@PrimaryKey
                             var id: Int = 0,
                             var logoPath: String = "",
                             var name: String = "",
                             var originCountry: String = ""
) {



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
