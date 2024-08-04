package com.example.loldex.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class DisneyDataResponse<T>(
    val info: DisneyInfoData,
    val data: T
)
