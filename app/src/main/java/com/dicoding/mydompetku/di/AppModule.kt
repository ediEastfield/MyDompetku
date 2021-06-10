package com.dicoding.mydompetku.di

import com.dicoding.mydompetku.core.domain.usecase.MyDompetkuInteractor
import com.dicoding.mydompetku.core.domain.usecase.MyDompetkuUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideMyDompetkuUseCase(myDompetkuInteractor: MyDompetkuInteractor): MyDompetkuUseCase
}