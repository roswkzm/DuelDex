package com.example.dueldex.core.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class CardListResponse(
    val data: List<CardDataResponse>
)
