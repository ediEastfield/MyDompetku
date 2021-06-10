package com.dicoding.mydompetku.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Report(
    val category : String,
    val total: Int
) : Parcelable
