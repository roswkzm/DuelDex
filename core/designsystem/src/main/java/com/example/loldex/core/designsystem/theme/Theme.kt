package com.example.loldex.core.designsystem.theme

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

private val LocalTypography = staticCompositionLocalOf { LdTypography(typography = Typography()) }

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    tertiary = Pink80,
    surface = Neutral20,
    surfaceContainer = Neutral30,
    secondary = Text10,
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    tertiary = Pink40,
    surface = Text0,
    surfaceContainer = Text10,
    secondary = Neutral10,
)

@Composable
fun LolDexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    disabledDynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
//        !disabledDynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val typography = LdTypography(typography = Typography())

    val defaultBackgroundTheme = BackgroundTheme(
        color = colorScheme.surface,
        tonalElevation = 2.dp
    )

    CompositionLocalProvider(
        LocalBackgroundTheme provides defaultBackgroundTheme,
        LocalTypography provides typography
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

val MaterialTheme.ldTypography: LdTypography
    @Composable
    @ReadOnlyComposable
    get() = LocalTypography.current
