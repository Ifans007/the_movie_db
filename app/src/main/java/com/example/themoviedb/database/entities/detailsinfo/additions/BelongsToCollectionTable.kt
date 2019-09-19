package com.example.themoviedb.database.entities.detailsinfo.additions

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BelongsToCollectionTable")
class BelongsToCollectionTable {

    @PrimaryKey
    var id: Int = -1
    lateinit var name: String
    lateinit var posterPath: String
    lateinit var backdropPath: String

    override fun equals(other: Any?): Boolean {
        return id == other
    }

    override fun hashCode(): Int {
        return id
    }

    override fun toString(): String {
        return "BelongsToCollectionModel (id: $id, name: $name)"
    }
}