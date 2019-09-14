package com.example.themoviedb.database.cache.additions

import com.example.themoviedb.database.dao.additions.ProductionCountryDao
import com.example.themoviedb.database.entities.additions.ProductionCountryTable
import com.example.themoviedb.retrofitservice.requests.models.ProductionCountry

object ProductionCountryCache {

    fun insert(
        productionCountryDao: ProductionCountryDao,
        productionCountriesList: List<ProductionCountry>,
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