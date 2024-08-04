package com.example.loldex.core.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class DigimonDetailResponse(
    val attributes: List<AttributeResponse>,                    // 속성
    val descriptions: List<DescriptionResponse>,                // 설명
    val fields: List<FieldResponse>,                            // 필드 정보
    val id: Int,                                                // 고유 ID
    val images: List<ImageResponse>,                    // 사진 정보
    val name: String,                                           // 이름
    val nextEvolutions: List<EvolutionResponse>,                // 이후 진화
    val priorEvolutions: List<EvolutionResponse>,               // 이전 진화
    val releaseDate: String,                                    // 출시 일자
    val skills: List<SkillResponse>,                            // 스킬 정보
    val types: List<TypeResponse>,                              // 타입 정보
)