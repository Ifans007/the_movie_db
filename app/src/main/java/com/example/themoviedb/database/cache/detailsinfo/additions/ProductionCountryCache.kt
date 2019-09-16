package com.example.themoviedb.database.cache.detailsinfo.additions

import com.example.themoviedb.database.dao.detailsinfo.additions.ProductionCountryDao
import com.example.themoviedb.database.entities.detailsinfo.additions.ProductionCountryTable
import com.example.themoviedb.retrofitservice.requests.models.detailsinfo.additions.ProductionCountryModel

object ProductionCountryCache {

    fun insert(
        productionCountryDao: ProductionCountryDao,
        productionCountriesList: List<ProductionCountryModel>,
        returnProductionCountryCodeList: (productionCountryCodeList: String) -> Unit
    ) {

        val productionCountryCodeList: MutableList<String> = mutableListOf()

        for (productionCountry in productionCountriesList) {

            val productionCountryTable = ProductionCountryTable()

            productionCountryTable.code = productionCountry.code
            productionCountryTable.name = productionCountry.name

            productionCountryDao.insert(productionCountryTable)

            productionCountryCodeList.add(productionCountry.code)
        }

        returnProductionCountryCodeList(productionCountryCodeList.toString())
    }
}