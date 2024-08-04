package com.example.loldex.core.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class SkillResponse(
    val description: String,
    val id: Int,
    val skill: String,
    val translation: String
)