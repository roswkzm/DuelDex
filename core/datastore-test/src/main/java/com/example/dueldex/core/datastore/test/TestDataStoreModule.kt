package com.example.dueldex.core.datastore.test

import androidx.datastore.core.DataStore
import com.example.dueldex.core.datastore.UserPreferences
import com.example.dueldex.core.datastore.UserPreferencesSerializer
import com.example.dueldex.core.datastore.di.DataStoreModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataStoreModule::class],
)
internal object TestDataStoreModule {
    @Provides
    @Singleton
    fun providesUserPreferencesDataStore(
        serializer: UserPreferencesSerializer,
    ): DataStore<UserPreferences> = InMemoryDataStore(serializer.defaultValue)
}