package com.example.themoviedb.database.entities.additions

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SpokenLanguageTable")
class SpokenLanguageTable(@PrimaryKey
                          var code: String = "",
                          var name: String? = null
) {
    override fun equals(other: Any?): Boolean {
        return code == other
    }

    override fun hashCode(): Int {
        return code!!.hashCode()
    }

    override fun toString(): String {
        return "SpokenLanguage (id: $code, name: $name)"
    }
}