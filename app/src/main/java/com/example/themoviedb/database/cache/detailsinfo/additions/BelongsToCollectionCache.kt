package com.example.themoviedb.database.cache.detailsinfo.additions

import com.example.themoviedb.database.dao.detailsinfo.additions.BelongsToCollectionDao
import com.example.themoviedb.database.entities.detailsinfo.additions.BelongsToCollectionTable
import com.example.themoviedb.retrofitservice.requests.models.detailsinfo.additions.BelongsToCollectionModel

object BelongsToCollectionCache {

    fun insert(
        belongsToCollectionDao: BelongsToCollectionDao,
        belongsToCollection: BelongsToCollectionModel,
        returnId: (id: Int) -> Unit
    ) {

        val belongsToCollectionTable = BelongsToCollectionTable()

        belongsToCollectionTable.id             = belongsToCollection.id
        belongsToCollectionTable.name           = belongsToCollection.name
        belongsToCollectionTable.posterPath     = belongsToCollection.posterPath ?: "-"
        belongsToCollectionTable.backdropPath   = belongsToCollection.backdropPath ?: "-"

        belongsToCollectionDao.insert(belongsToCollectionTable)

        returnId(belongsToCollection.id)
    }
}