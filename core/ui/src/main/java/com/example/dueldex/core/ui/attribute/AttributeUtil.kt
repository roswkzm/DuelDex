package com.example.dueldex.core.ui.attribute

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.dueldex.core.designsystem.theme.AttributeDark
import com.example.dueldex.core.designsystem.theme.AttributeDivine
import com.example.dueldex.core.designsystem.theme.AttributeEarth
import com.example.dueldex.core.designsystem.theme.AttributeFire
import com.example.dueldex.core.designsystem.theme.AttributeLight
import com.example.dueldex.core.designsystem.theme.AttributeWater
import com.example.dueldex.core.designsystem.theme.AttributeWind
import com.example.dueldex.core.designsystem.theme.ddTypography
import com.example.dueldex.core.model.enums.AttributeType
import com.example.dueldex.core.designsystem.R as DesignR

fun getAttributeTagImage(
    attribute: String,
): Int {
    val attributeType = AttributeType.valueOf(attribute)
    return when (attributeType) {
        AttributeType.DARK -> DesignR.drawable.attribute_dark
        AttributeType.LIGHT -> DesignR.drawable.attribute_light
        AttributeType.FIRE -> DesignR.drawable.attribute_fire
        AttributeType.WATER -> DesignR.drawable.attribute_water
        AttributeType.WIND -> DesignR.drawable.attribute_wind
        AttributeType.EARTH -> DesignR.drawable.attribute_earth
        AttributeType.DIVINE -> DesignR.drawable.attribute_divine
    }
}

fun getAttributeTagColor(
    attribute: String,
): Color {
    val attributeType = AttributeType.valueOf(attribute)
    return when (attributeType) {
        AttributeType.DARK -> AttributeDark
        AttributeType.LIGHT -> AttributeLight
        AttributeType.FIRE -> AttributeFire
        AttributeType.WATER -> AttributeWater
        AttributeType.WIND -> AttributeWind
        AttributeType.EARTH -> AttributeEarth
        AttributeType.DIVINE -> AttributeDivine
    }
}

fun getAttributeIconSize(attributeSize: AttributeSize): Dp {
    return when (attributeSize) {
        AttributeSize.L -> {
            28.dp
        }

        AttributeSize.S -> {
            22.dp
        }
    }
}

@Composable
fun getAttributeTextStyle(attributeSize: AttributeSize): TextStyle {
    return when (attributeSize) {
        AttributeSize.L -> {
            MaterialTheme.ddTypography.fontLabelM
        }

        AttributeSize.S -> {
            MaterialTheme.ddTypography.fontLabelS
        }
    }
}

enum class AttributeSize {
    L,
    S
}