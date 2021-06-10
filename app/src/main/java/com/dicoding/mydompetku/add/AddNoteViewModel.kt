package com.dicoding.mydompetku.add

import androidx.lifecycle.ViewModel
import com.dicoding.mydompetku.core.domain.model.Cashflow
import com.dicoding.mydompetku.core.domain.usecase.MyDompetkuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(private val myDompetkuUseCase: MyDompetkuUseCase) : ViewModel() {

    fun insertCashflow(cashflow: Cashflow) = myDompetkuUseCase.insertCashflow(cashflow)

}