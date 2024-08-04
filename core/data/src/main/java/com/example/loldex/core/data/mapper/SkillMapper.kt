package com.example.loldex.core.data.mapper

import com.example.loldex.core.model.SkillData
import com.example.loldex.core.network.model.response.SkillResponse

fun SkillResponse.toData() = SkillData(
    id = id,
    description = description,
    skill = skill,
    translation = translation
)