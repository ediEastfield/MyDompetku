package com.dicoding.mydompetku.core.data.source.local

import com.dicoding.mydompetku.core.data.source.local.entity.CashflowEntity
import com.dicoding.mydompetku.core.data.source.local.entity.CategoryEntity
import com.dicoding.mydompetku.core.data.source.local.entity.ReportEntity
import com.dicoding.mydompetku.core.data.source.local.room.MyDompetkuDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val myDompetkuDao: MyDompetkuDao) {

    fun getAllCategory(jenis: String): Flow<List<CategoryEntity>> =
        if (jenis == "debt&loan") {
            myDompetkuDao.getDebtLoanCategory()
        } else {
            myDompetkuDao.getAllCategory(jenis)
        }

    suspend fun insertCategory(categoryList: List<CategoryEntity>) = myDompetkuDao.insertCategory(categoryList)

    fun getCashflowByMonth(month: String): Flow<List<CashflowEntity>> = myDompetkuDao.getCashflowByMonth(month)

    suspend fun insertAllCashflow(cashflowList: List<CashflowEntity>) = myDompetkuDao.insertAllCashflow(cashflowList)

    fun insertCashflow(cashflow: CashflowEntity) = myDompetkuDao.insertCashflow(cashflow)

    fun setUpdateCashflow(cashflow: CashflowEntity) {
        myDompetkuDao.updateCashflow(cashflow)
    }

    fun setDeleteCashflow(cashflow: CashflowEntity) {
        myDompetkuDao.deleteCashflow(cashflow)
    }

    fun getTotalNominal(jenis: String): Flow<Int> = myDompetkuDao.getTotalNominal(jenis)

    fun getReportData(jenis: String): Flow<List<ReportEntity>> = myDompetkuDao.getReportData(jenis)

}