package com.dicoding.mydompetku.home.lastMonth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.mydompetku.core.domain.usecase.MyDompetkuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LastMonthViewModel @Inject constructor(private val myDompetkuUseCase: MyDompetkuUseCase) : ViewModel() {
    fun getLastMonthCashflow(month: String) = myDompetkuUseCase.getAllCashflow(month).asLiveData()
}