package com.example.loldex.core.network.di

import com.example.loldex.core.network.DigimonNetworkDataSource
import com.example.loldex.core.network.retrofit.RetrofitDigimonNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FlavoredNetworkModule {

    @Binds
    fun bindsDigimonNetworkDataSource(impl: RetrofitDigimonNetwork): DigimonNetworkDataSource
}