package com.example.dueldex.core.data.repository

import com.example.dueldex.core.model.YugiohCardData
import com.example.dueldex.core.network.model.response.CardPagingListResponse
import kotlinx.coroutines.flow.Flow

interface YugiohRepository {

    fun getYugiohPagingList(
        num: Int,
        offset: Int,
    ): Flow<CardPagingListResponse>

    fun getYugiohCardDataById(
        id: Long
    ): Flow<YugiohCardData>

    fun getYugiohCardDataByName(
        name: String
    ): Flow<YugiohCardData>

    fun getYugiohCardDataBySearchString(
        searchString: String,
        type: String?,
        attribute: String?,
        race: String?,
        effect: String?,
        level: Int?,
        onError: (String) -> Unit,
    ): Flow<List<YugiohCardData>>
}