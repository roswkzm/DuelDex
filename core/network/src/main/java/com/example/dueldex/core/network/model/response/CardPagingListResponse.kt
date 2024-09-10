package com.example.dueldex.core.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class CardPagingListResponse(
    val data: List<CardDataResponse>,
    val meta: MetaResponse
)