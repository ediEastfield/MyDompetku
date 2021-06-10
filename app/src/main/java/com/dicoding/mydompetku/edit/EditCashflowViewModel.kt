package com.dicoding.mydompetku.edit

import androidx.lifecycle.ViewModel
import com.dicoding.mydompetku.core.domain.model.Cashflow
import com.dicoding.mydompetku.core.domain.usecase.MyDompetkuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditCashflowViewModel @Inject constructor(private val myDompetkuUseCase: MyDompetkuUseCase) : ViewModel() {
    fun setUpdateCashflow(cashflow: Cashflow) = myDompetkuUseCase.setUpdateCashflow(cashflow)
}