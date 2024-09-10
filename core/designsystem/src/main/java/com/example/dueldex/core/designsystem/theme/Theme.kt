package com.example.dueldex.core.designsystem.theme

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.Locale

private val LocalTypography = staticCompositionLocalOf { DdTypography(typography = Typography()) }
val LocalLocale = staticCompositionLocalOf { Locale.getDefault() }

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    surface = Neutral20,
    surfaceContainer = Neutral30,
    secondary = Text10,
    error = Error,
    tertiary = Neutral10,
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    surface = Text0,
    surfaceContainer = Text10,
    secondary = Neutral10,
    error = Error,
    tertiary = Gray300,
)

@Composable
fun DuelDexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    locale: Locale = Locale.getDefault(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val typography = DdTypography(typography = Typography())

    val defaultBackgroundTheme = BackgroundTheme(
        color = colorScheme.surface,
        tonalElevation = 2.dp
    )

    CompositionLocalProvider(
        LocalBackgroundTheme provides defaultBackgroundTheme,
        LocalTypography provides typography,
        LocalLocale provides locale
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography.typography,
            content = content,
            shapes = Shapes
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme")
annotation class ThemePreviews

val MaterialTheme.ddTypography: DdTypography
    @Composable
    @ReadOnlyComposable
    get() = LocalTypography.current
