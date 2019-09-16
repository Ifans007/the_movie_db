package com.example.themoviedb.database.entities.detailsinfo.additions

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BelongsToCollectionTable")
class BelongsToCollectionTable(@PrimaryKey
                               var id: Int? = null,
                               var name: String? = null,
                               var posterPath: String? = null,
                               var backdropPath: String? = null
) {
    override fun equals(other: Any?): Boolean {
        return id == other
    }

    override fun hashCode(): Int {
        return id!!
    }

    override fun toString(): String {
        return "BelongsToCollectionModel (id: $id, name: $name)"
    }
}