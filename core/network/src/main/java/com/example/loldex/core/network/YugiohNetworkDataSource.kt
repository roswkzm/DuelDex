package com.example.loldex.core.network

import com.example.loldex.core.network.model.response.CardListResponse
import com.skydoves.sandwich.ApiResponse

interface YugiohNetworkDataSource {

    suspend fun getYugiohList(
        num: Int,
        offset: Int,
    ): ApiResponse<CardListResponse>
}