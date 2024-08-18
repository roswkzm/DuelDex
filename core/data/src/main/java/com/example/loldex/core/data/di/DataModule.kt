package com.example.loldex.core.data.di

import com.example.loldex.core.data.repository.DecksRepository
import com.example.loldex.core.data.repository.DecksRepositoryImpl
import com.example.loldex.core.data.repository.RecentSearchRepository
import com.example.loldex.core.data.repository.RecentSearchRepositoryImpl
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
    abstract fun bindRecentSearchRepository(
        recentSearchRepositoryImpl: RecentSearchRepositoryImpl
    ): RecentSearchRepository

    @Binds
    abstract fun bindDecksRepository(
        decksRepositoryImpl: DecksRepositoryImpl
    ): DecksRepository
}