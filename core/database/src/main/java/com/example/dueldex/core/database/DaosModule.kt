package com.example.dueldex.core.database

import com.example.dueldex.core.database.dao.DeckDao
import com.example.dueldex.core.database.dao.YugiohCardDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {

    @Provides
    fun providesYugiohCardDao(
        dataBase: DdDataBase
    ): YugiohCardDao = dataBase.yugiohCardDao()

    @Provides
    fun providesDeckDao(
        dataBase: DdDataBase
    ): DeckDao = dataBase.deckDao()
}