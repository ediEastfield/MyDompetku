package com.dicoding.mydompetku.core.domain.repository

import com.dicoding.mydompetku.core.data.Resource
import com.dicoding.mydompetku.core.domain.model.Cashflow
import com.dicoding.mydompetku.core.domain.model.Category
import com.dicoding.mydompetku.core.domain.model.Report
import kotlinx.coroutines.flow.Flow

interface IMyDompetkuRepository {

    fun getAllCategory(jenis: String): Flow<Resource<List<Category>>>
    fun getAllCashflow(month: String): Flow<Resource<List<Cashflow>>>
    fun insertCashflow(cashflow: Cashflow)
    fun setUpdateCashflow(cashflow: Cashflow)
    fun setDeleteCashflow(cashflow: Cashflow)
    fun getTotalNominal(jenis: String): Flow<Int>
    fun getReportData(jenis: String): Flow<List<Report>>
}