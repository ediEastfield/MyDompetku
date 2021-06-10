package com.dicoding.mydompetku.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cashflow(
    val cashflowId: String,
    val icon: String,
    val category: String,
    val jenis: String,
    val note: String,
    val date: String,
    val nominal: Int
):Parcelable
