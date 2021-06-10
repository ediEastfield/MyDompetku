package com.dicoding.mydompetku.category.income

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.mydompetku.core.domain.usecase.MyDompetkuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IncomeCategoryViewModel @Inject constructor(myDompetkuUseCase: MyDompetkuUseCase) : ViewModel() {
    val incomeCategory = myDompetkuUseCase.getAllCategory("income").asLiveData()
}