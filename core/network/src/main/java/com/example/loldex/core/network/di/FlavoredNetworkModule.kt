package com.example.loldex.core.network.di

import com.example.loldex.core.network.DisneyNetworkDataSource
import com.example.loldex.core.network.retrofit.RetrofitDisneyNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FlavoredNetworkModule {

    @Binds
    fun bindsDisneyNetworkDataSource(impl: RetrofitDisneyNetwork): DisneyNetworkDataSource
}