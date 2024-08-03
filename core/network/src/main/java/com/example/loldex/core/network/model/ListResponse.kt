package com.example.loldex.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ListResponse<T>(
    val info: ListInfoData,
    val data: T
)
