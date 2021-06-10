package com.dicoding.mydompetku.core.di

import android.content.Context
import androidx.room.Room
import com.dicoding.mydompetku.core.data.source.local.room.MyDompetkuDao
import com.dicoding.mydompetku.core.data.source.local.room.MyDompetkuDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MyDompetkuDatabase = Room.databaseBuilder(
        context,
        MyDompetkuDatabase::class.java,
        "MyDompetku.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideMyDompetkuDao(database: MyDompetkuDatabase): MyDompetkuDao = database.myDompetkuDao()
}