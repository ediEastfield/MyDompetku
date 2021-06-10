package com.dicoding.mydompetku.home.thisMonth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.mydompetku.core.domain.usecase.MyDompetkuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ThisMonthViewModel @Inject constructor(private val myDompetkuUseCase: MyDompetkuUseCase) : ViewModel() {
    fun getThisMonthCashflow(month: String) = myDompetkuUseCase.getAllCashflow(month).asLiveData()
}