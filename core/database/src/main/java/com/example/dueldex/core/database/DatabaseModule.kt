package com.example.dueldex.core.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun providesDdDatabase(
        @ApplicationContext context: Context,
    ): DdDataBase = Room.databaseBuilder(
        context,
        DdDataBase::class.java,
        "dd-database"
    ).build()
}