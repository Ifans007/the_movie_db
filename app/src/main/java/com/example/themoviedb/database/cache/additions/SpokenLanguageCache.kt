package com.example.themoviedb.database.cache.additions

import com.example.themoviedb.database.dao.additions.SpokenLanguageDao
import com.example.themoviedb.database.entities.additions.SpokenLanguageTable
import com.example.themoviedb.retrofitservice.requests.models.SpokenLanguage

object SpokenLanguageCache {

    fun insert(
        spokenLanguageDao: SpokenLanguageDao,
        spokenLanguagesList: List<SpokenLanguage>,
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