package com.example.loldex.core.model

data class DigimonDetailData(
    val id: Int,                                            // 고유 ID
    val name: String,                                       // 이름
    val attributes: List<AttributeData>,                    // 속성
    val descriptions: List<DescriptionData>,                // 설명
    val fields: List<FieldData>,                            // 필드 정보
    val imageResponses: List<ImageData>,                    // 사진 정보
    val nextEvolutions: List<EvolutionData>,                // 이후 진화
    val priorEvolutions: List<EvolutionData>,               // 이전 진화
    val releaseDate: String,                                // 출시 일자
    val skills: List<SkillData>,                            // 스킬 정보
    val types: List<TypeData>,                              // 타입 정보
)