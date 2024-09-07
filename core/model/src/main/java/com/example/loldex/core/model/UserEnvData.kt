package com.example.loldex.core.model

import com.example.loldex.core.model.enums.LocalizationConfig
import com.example.loldex.core.model.enums.ThemeConfig

data class UserEnvData(
    val themeConfig: ThemeConfig,
    val localizationConfig: LocalizationConfig,
)
