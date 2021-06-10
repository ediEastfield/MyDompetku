package com.dicoding.mydompetku.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.mydompetku.core.data.source.local.entity.CashflowEntity
import com.dicoding.mydompetku.core.data.source.local.entity.CategoryEntity

@Database(
    entities = [CategoryEntity::class, CashflowEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MyDompetkuDatabase : RoomDatabase() {

    abstract fun myDompetkuDao(): MyDompetkuDao

}