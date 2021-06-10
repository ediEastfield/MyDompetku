package com.dicoding.mydompetku.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "categoryId")
    var categoryId: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "icon")
    var icon: String,

    @ColumnInfo(name = "jenis")
    var jenis: String
)