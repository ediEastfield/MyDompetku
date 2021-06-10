package com.dicoding.mydompetku.category.expense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.mydompetku.core.domain.usecase.MyDompetkuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExpenseCategoryViewModel @Inject constructor(myDompetkuUseCase: MyDompetkuUseCase) : ViewModel() {
    val expenseCategory = myDompetkuUseCase.getAllCategory("expense").asLiveData()
}