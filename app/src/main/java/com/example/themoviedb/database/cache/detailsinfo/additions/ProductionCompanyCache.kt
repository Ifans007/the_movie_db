package com.example.themoviedb.database.cache.detailsinfo.additions

import com.example.themoviedb.database.dao.detailsinfo.additions.ProductionCompanyDao
import com.example.themoviedb.database.entities.detailsinfo.additions.ProductionCompanyTable
import com.example.themoviedb.retrofitservice.requests.models.detailsinfo.additions.ProductionCompanyModel

object ProductionCompanyCache {

    fun insert(
        productionCompanyDao: ProductionCompanyDao,
        productionCompanies: List<ProductionCompanyModel>,
        returnProductionCompanyIdList: (productionCompanyList: String) -> Unit
    ) {

        val productionCompanyList: MutableList<Int> = mutableListOf()

        for (productionCompany in productionCompanies) {

            val productionCompanyTable = ProductionCompanyTable()

            productionCompanyTable.id            = productionCompany.id
            productionCompanyTable.logoPath      = productionCompany.logoPath
            productionCompanyTable.name          = productionCompany.name
            productionCompanyTable.originCountry = productionCompany.originCountry

            productionCompanyDao.insert(productionCompanyTable)

            productionCompanyList.add(productionCompany.id)
        }

        returnProductionCompanyIdList(productionCompanyList.toString())
    }
}