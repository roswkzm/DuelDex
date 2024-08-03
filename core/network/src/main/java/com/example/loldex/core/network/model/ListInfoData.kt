package com.example.loldex.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ListInfoData(
    val count: Int,
    val totalPages: Int?,
    val previousPage: String?,
    val nextPage: String?,
)
