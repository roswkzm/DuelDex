package com.example.dueldex.core.model

import com.example.dueldex.core.model.enums.LocalizationConfig
import com.example.dueldex.core.model.enums.ThemeConfig

data class UserEnvData(
    val themeConfig: ThemeConfig,
    val localizationConfig: LocalizationConfig,
)
