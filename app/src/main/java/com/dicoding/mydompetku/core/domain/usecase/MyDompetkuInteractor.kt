package com.dicoding.mydompetku.core.domain.usecase

import com.dicoding.mydompetku.core.domain.model.Cashflow
import com.dicoding.mydompetku.core.domain.model.Report
import com.dicoding.mydompetku.core.domain.repository.IMyDompetkuRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MyDompetkuInteractor @Inject constructor(private val myDompetkuRepository: IMyDompetkuRepository) : MyDompetkuUseCase {

    override fun getAllCategory(jenis: String) = myDompetkuRepository.getAllCategory(jenis)

    override fun getAllCashflow(month: String) = myDompetkuRepository.getAllCashflow(month)

    override fun insertCashflow(cashflow: Cashflow) = myDompetkuRepository.insertCashflow(cashflow)

    override fun setUpdateCashflow(cashflow: Cashflow) = myDompetkuRepository.setUpdateCashflow(cashflow)

    override fun setDeleteCashflow(cashflow: Cashflow) = myDompetkuRepository.setDeleteCashflow(cashflow)

    override fun getTotalNominal(jenis: String) = myDompetkuRepository.getTotalNominal(jenis)

    override fun getReportData(jenis: String) = myDompetkuRepository.getReportData(jenis)
}