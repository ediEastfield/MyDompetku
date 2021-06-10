package com.dicoding.mydompetku.core.di

import com.dicoding.mydompetku.core.data.MyDompetkuRepository
import com.dicoding.mydompetku.core.domain.repository.IMyDompetkuRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(myDompetkuRepository: MyDompetkuRepository): IMyDompetkuRepository
}