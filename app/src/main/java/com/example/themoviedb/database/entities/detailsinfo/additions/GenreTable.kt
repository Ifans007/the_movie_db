package com.example.themoviedb.database.entities.detailsinfo.additions

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GenreTable")
class GenreTable {

    @PrimaryKey
    var id: Int = -1
    lateinit var name: String

    override fun equals(other: Any?): Boolean {
        return id == other
    }

    override fun hashCode(): Int {
        return id
    }

    override fun toString(): String {
        return "Genre (id: $id, name: $name)"
    }
}