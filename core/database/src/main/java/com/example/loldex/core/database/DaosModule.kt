package com.example.loldex.core.database

import com.example.loldex.core.database.dao.YugiohCardDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {

    @Provides
    fun providesYugiohCardDao(
        dataBase: LdDataBase
    ): YugiohCardDao = dataBase.yugiohCardDao()
}