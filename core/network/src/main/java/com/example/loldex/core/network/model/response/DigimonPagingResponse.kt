package com.example.loldex.core.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class DigimonPagingResponse(
    val content: List<DigimonContentResponse>,
    val pageable: Pageable
)

@Serializable
data class DigimonContentResponse(
    val href: String,
    val id: Int,
    val image: String,
    val name: String
)

@Serializable
data class Pageable(
    val currentPage: Int,
    val elementsOnPage: Int,
    val nextPage: String,
    val previousPage: String,
    val totalElements: Int,
    val totalPages: Int
)