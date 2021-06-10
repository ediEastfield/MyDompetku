package com.dicoding.mydompetku.report

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.mydompetku.core.domain.usecase.MyDompetkuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(private val myDompetkuUseCase: MyDompetkuUseCase) : ViewModel() {
    fun totalNominal(jenis: String) = myDompetkuUseCase.getTotalNominal(jenis).asLiveData()
    fun reportData(jenis: String) = myDompetkuUseCase.getReportData(jenis).asLiveData()
}