package com.dicoding.mydompetku.detail.cashflow

import androidx.lifecycle.ViewModel
import com.dicoding.mydompetku.core.domain.model.Cashflow
import com.dicoding.mydompetku.core.domain.usecase.MyDompetkuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailCashflowViewModel @Inject constructor(private val myDompetkuUseCase: MyDompetkuUseCase) : ViewModel() {
    fun setDeleteCashflow(cashflow: Cashflow) = myDompetkuUseCase.setDeleteCashflow(cashflow)
}