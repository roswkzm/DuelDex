package com.example.dueldex.core.network

import com.example.dueldex.core.network.model.response.CardListResponse
import com.example.dueldex.core.network.model.response.CardPagingListResponse
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
        level: Int?,
    ): ApiResponse<CardListResponse>
}