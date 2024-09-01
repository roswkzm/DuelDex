package com.example.loldex.core.network

import com.example.loldex.core.network.model.response.CardListResponse
import com.example.loldex.core.network.model.response.CardPagingListResponse
import com.skydoves.sandwich.ApiResponse

interface YugiohNetworkDataSource {

    suspend fun getYugiohPagingList(
        num: Int,
        offset: Int,
    ): ApiResponse<CardPagingListResponse>

    suspend fun getYugiohCardDataById(
        id: Long
    ): ApiResponse<CardListResponse>

    suspend fun getYugiohCardDataByName(
        name: String
    ): ApiResponse<CardListResponse>

    suspend fun getYugiohCardDataBySearchString(
        searchString: String,
        type: String?,
        attribute: String?,
        race: String?,
        effect: String?,
    ): ApiResponse<CardListResponse>
}