package com.example.themoviedb.database.cache.detailsinfo.additions

import com.example.themoviedb.database.dao.detailsinfo.additions.SpokenLanguageDao
import com.example.themoviedb.database.entities.detailsinfo.additions.SpokenLanguageTable
import com.example.themoviedb.retrofitservice.requests.models.detailsinfo.additions.SpokenLanguageModel

object SpokenLanguageCache {

    fun insert(
        spokenLanguageDao: SpokenLanguageDao,
        spokenLanguagesList: List<SpokenLanguageModel>,
        returnSpokenLanguageCodeList: (spokenLanguageCodeList: String) -> Unit
    ) {

        val spokenLanguageCodeList: MutableList<String> = mutableListOf()

        for (spokenLanguageCode in spokenLanguagesList) {

            val spokenLanguageCodeTable = SpokenLanguageTable()

            spokenLanguageCodeTable.code = spokenLanguageCode.code
            spokenLanguageCodeTable.name = spokenLanguageCode.name

            spokenLanguageDao.insert(spokenLanguageCodeTable)

            spokenLanguageCodeList.add(spokenLanguageCode.code)
        }

        returnSpokenLanguageCodeList(spokenLanguageCodeList.toString())
    }
}