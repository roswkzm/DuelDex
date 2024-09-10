package com.example.dueldex.core.data.di

import com.example.dueldex.core.data.repository.DecksRepository
import com.example.dueldex.core.data.repository.DecksRepositoryImpl
import com.example.dueldex.core.data.repository.UserDataRepository
import com.example.dueldex.core.data.repository.UserDataRepositoryImpl
import com.example.dueldex.core.data.repository.YugiohRepository
import com.example.dueldex.core.data.repository.YugiohRepositoryImpl
import com.example.dueldex.core.data.util.ConnectivityManagerNetworkMonitor
import com.example.dueldex.core.data.util.NetworkMonitor
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

    @Binds
    internal abstract fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor
}