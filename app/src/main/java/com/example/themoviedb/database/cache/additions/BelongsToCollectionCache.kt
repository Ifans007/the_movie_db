package com.example.themoviedb.database.cache.additions

import com.example.themoviedb.database.dao.additions.BelongsToCollectionDao
import com.example.themoviedb.database.entities.additions.BelongsToCollectionTable
import com.example.themoviedb.retrofitservice.requests.models.BelongsToCollection

object BelongsToCollectionCache {

    fun insert(
        belongsToCollectionDao: BelongsToCollectionDao,
        belongsToCollection: BelongsToCollection,
        returnId: (id: Int) -> Unit
    ) {

        val belongsToCollectionTable = BelongsToCollectionTable()

        belongsToCollectionTable.id             = belongsToCollection.id
        belongsToCollectionTable.name           = belongsToCollection.name
        belongsToCollectionTable.posterPath     = belongsToCollection.posterPath
        belongsToCollectionTable.backdropPath   = belongsToCollection.backdropPath

        belongsToCollectionDao.insert(belongsToCollectionTable)

        returnId(belongsToCollection.id)
    }
}