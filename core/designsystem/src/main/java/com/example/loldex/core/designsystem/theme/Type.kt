package com.example.loldex.core.designsystem.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.loldex.core.designsystem.R
import com.example.loldex.core.designsystem.component.LdBackGround

data class LdTypography(
    val typography: Typography,
    val fontTitleXL: TextStyle = titleXLargeTextStyle,
    val fontTitleL: TextStyle = titleLargeTextStyle,
    val fontTitleM: TextStyle = titleMediumTextStyle,
    val fontTitleS: TextStyle = titleSmallTextStyle,
    val fontTitleXS: TextStyle = titleXSmallTextStyle,
    val fontBodyL: TextStyle = bodyLargeTextStyle,
    val fontBodyM: TextStyle = bodyMediumTextStyle,
    val fontBodyS: TextStyle = bodySmallTextStyle,
    val fontLabelXL: TextStyle = labelXLargeTextStyle,
    val fontLabelL: TextStyle = labelLargeTextStyle,
    val fontLabelM: TextStyle = labelMediumTextStyle,
    val fontLabelS: TextStyle = labelSmallTextStyle,
)

val pretendardFont = FontFamily(
    Font(R.font.pretendard_variable)
)

// Title
val titleXLargeTextStyle = TextStyle(
    fontFamily = pretendardFont,
    fontWeight = FontWeight.SemiBold,
    fontSize = 32.sp,
    lineHeight = 42.sp,
)

val titleLargeTextStyle = TextStyle(
    fontFamily = pretendardFont,
    fontWeight = FontWeight.SemiBold,
    fontSize = 24.sp,
    lineHeight = 32.sp,
)

val titleMediumTextStyle = TextStyle(
    fontFamily = pretendardFont,
    fontWeight = FontWeight.SemiBold,
    fontSize = 20.sp,
    lineHeight = 32.sp,
)

val titleSmallTextStyle = TextStyle(
    fontFamily = pretendardFont,
    fontWeight = FontWeight.SemiBold,
    fontSize = 18.sp,
    lineHeight = 24.sp,
)

val titleXSmallTextStyle = TextStyle(
    fontFamily = pretendardFont,
    fontWeight = FontWeight.SemiBold,
    fontSize = 16.sp,
    lineHeight = 22.sp,
)

// Body
val bodyLargeTextStyle = TextStyle(
    fontFamily = pretendardFont,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 24.sp,
)

val bodyMediumTextStyle = TextStyle(
    fontFamily = pretendardFont,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 22.sp,
)

val bodySmallTextStyle = TextStyle(
    fontFamily = pretendardFont,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    lineHeight = 18.sp,
)

// Label
val labelXLargeTextStyle = TextStyle(
    fontFamily = pretendardFont,
    fontWeight = FontWeight.Medium,
    fontSize = 18.sp,
    lineHeight = 28.sp,
)

val labelLargeTextStyle = TextStyle(
    fontFamily = pretendardFont,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
    lineHeight = 24.sp,
)

val labelMediumTextStyle = TextStyle(
    fontFamily = pretendardFont,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
    lineHeight = 22.sp,
)

val labelSmallTextStyle = TextStyle(
    fontFamily = pretendardFont,
    fontWeight = FontWeight.Medium,
    fontSize = 12.sp,
    lineHeight = 18.sp,
)

@ThemePreviews
@Composable
fun LdFontTextPreview() {
    LolDexTheme {
        LdBackGround {
            Column {
                Text(text = "Hello Android!", style = MaterialTheme.ldTypography.fontTitleXL)
                Text(text = "Hello Android!", style = MaterialTheme.ldTypography.fontTitleL)
                Text(text = "Hello Android!", style = MaterialTheme.ldTypography.fontTitleM)
                Text(text = "Hello Android!", style = MaterialTheme.ldTypography.fontTitleS)
                Text(text = "Hello Android!", style = MaterialTheme.ldTypography.fontTitleXS)
                Text(text = "Hello Android!", style = MaterialTheme.ldTypography.fontBodyL)
                Text(text = "Hello Android!", style = MaterialTheme.ldTypography.fontBodyM)
                Text(text = "Hello Android!", style = MaterialTheme.ldTypography.fontBodyS)
                Text(text = "Hello Android!", style = MaterialTheme.ldTypography.fontLabelXL)
                Text(text = "Hello Android!", style = MaterialTheme.ldTypography.fontLabelL)
                Text(text = "Hello Android!", style = MaterialTheme.ldTypography.fontLabelM)
                Text(text = "Hello Android!", style = MaterialTheme.ldTypography.fontLabelS)
            }
        }
    }
}