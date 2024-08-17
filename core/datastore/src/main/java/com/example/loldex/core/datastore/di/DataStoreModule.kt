package com.example.loldex.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.example.loldex.core.common.network.Dispatcher
import com.example.loldex.core.common.network.LdDispatchers.IO
import com.example.loldex.core.common.network.di.ApplicationScope
import com.example.loldex.core.datastore.RecentSearchPreferences
import com.example.loldex.core.datastore.RecentSearchPreferencesSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataStoreModule {

    @Provides
    @Singleton
    internal fun providesRecentSearchPreferencesDataStore(
        @ApplicationContext context: Context,
        @Dispatcher(IO) ioDispatcher: CoroutineDispatcher,
        @ApplicationScope scope: CoroutineScope,
        recentSearchPreferencesSerializer: RecentSearchPreferencesSerializer,
    ): DataStore<RecentSearchPreferences> =
        DataStoreFactory.create(
            serializer = recentSearchPreferencesSerializer,
            scope = CoroutineScope(scope.coroutineContext + ioDispatcher),
        ) {
            context.dataStoreFile("recent_search_preferences.pb")
        }
}