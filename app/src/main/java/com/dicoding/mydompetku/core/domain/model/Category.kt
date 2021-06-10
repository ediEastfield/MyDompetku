package com.dicoding.mydompetku.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val categoryId: String,
    val title: String,
    val icon: String,
    val jenis: String
) : Parcelable
