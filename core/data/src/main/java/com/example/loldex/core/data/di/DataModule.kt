package com.example.loldex.core.data.di

import com.example.loldex.core.data.repository.DecksRepository
import com.example.loldex.core.data.repository.DecksRepositoryImpl
import com.example.loldex.core.data.repository.UserDataRepository
import com.example.loldex.core.data.repository.UserDataRepositoryImpl
import com.example.loldex.core.data.repository.YugiohRepository
import com.example.loldex.core.data.repository.YugiohRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataModule {

    @Binds
    abstract fun bindYugiohRepository(
        yugiohRepositoryImpl: YugiohRepositoryImpl
    ): YugiohRepository

    @Binds
    abstract fun bindUserDataRepository(
        userDataRepositoryImpl: UserDataRepositoryImpl
    ): UserDataRepository

    @Binds
    abstract fun bindDecksRepository(
        decksRepositoryImpl: DecksRepositoryImpl
    ): DecksRepository
}