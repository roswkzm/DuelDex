package com.example.loldex.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class DataResponse<T>(
    val info: InfoData,
    val data: T
)
