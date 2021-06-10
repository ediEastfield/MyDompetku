package com.dicoding.mydompetku.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cashflow")
data class CashflowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "cashflowId")
    var cashflowId : String,

    @ColumnInfo(name = "icon")
    var icon: String,

    @ColumnInfo(name = "category")
    var category: String,

    @ColumnInfo(name = "jenis")
    var jenis: String,

    @ColumnInfo(name = "notes")
    var notes: String,

    @ColumnInfo(name = "date")
    var date: String,

    @ColumnInfo(name = "nominal")
    var nominal: Int
)
