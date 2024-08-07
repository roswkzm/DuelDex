package com.example.loldex.core.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class CardListResponse(
    val data: List<CardResponse>,
    val meta: MetaResponse
)