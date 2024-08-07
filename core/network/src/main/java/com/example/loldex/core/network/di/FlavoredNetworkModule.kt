package com.example.loldex.core.network.di

import com.example.loldex.core.network.YugiohNetworkDataSource
import com.example.loldex.core.network.retrofit.RetrofitYugiohNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FlavoredNetworkModule {

    @Binds
    fun bindYugiohNetworkDataSource(impl: RetrofitYugiohNetwork): YugiohNetworkDataSource
}