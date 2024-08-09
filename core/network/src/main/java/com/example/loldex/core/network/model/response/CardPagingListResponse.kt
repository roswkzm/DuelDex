package com.example.loldex.core.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class CardPagingListResponse(
    val data: List<CardDataResponse>,
    val meta: MetaResponse
)