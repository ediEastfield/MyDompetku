package com.dicoding.mydompetku.category.debt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.mydompetku.core.domain.usecase.MyDompetkuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DebtCategoryViewModel @Inject constructor(myDompetkuUseCase: MyDompetkuUseCase) : ViewModel() {
    val debtCategory = myDompetkuUseCase.getAllCategory("debt&loan").asLiveData()
}