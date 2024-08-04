package com.example.loldex.core.data.di

import com.example.loldex.core.data.repository.DigimonRepository
import com.example.loldex.core.data.repository.DigimonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataModule {

    @Binds
    abstract fun bindDigimonRepository(
        digimonRepositoryImpl: DigimonRepositoryImpl
    ): DigimonRepository
}