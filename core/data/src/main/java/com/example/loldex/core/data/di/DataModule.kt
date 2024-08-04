package com.example.loldex.core.data.di

import com.example.loldex.core.data.repository.DisneyRepository
import com.example.loldex.core.data.repository.DisneyRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataModule {

    @Binds
    abstract fun bindDisneyRepository(
        disneyRepositoryImpl: DisneyRepositoryImpl
    ): DisneyRepository
}