package com.example.dueldex

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.dueldex.MainActivityUiState.Loading
import com.example.dueldex.MainActivityUiState.Success
import com.example.dueldex.core.data.util.NetworkMonitor
import com.example.dueldex.core.designsystem.component.DdBackGround
import com.example.dueldex.core.designsystem.theme.DuelDexTheme
import com.example.dueldex.core.model.enums.LocalizationConfig
import com.example.dueldex.core.model.enums.ThemeConfig
import com.example.dueldex.ui.MainScreen
import com.example.dueldex.ui.rememberAppState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        var uiState: MainActivityUiState by mutableStateOf(Loading)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState
                    .onEach { uiState = it }
                    .collect()
            }
        }

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT,
            ),
            navigationBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT,
            )
        )

        setContent {
            val appState = rememberAppState(
                networkMonitor = networkMonitor,
            )

            val darkTheme = shouldUseDarkTheme(uiState = uiState)
            val locale = shouldUseLocalization(uiState = uiState)

            DuelDexTheme(
                darkTheme = darkTheme,
                locale = locale,
            ) {
                DdBackGround {
                    MainScreen(appState = appState)
                }
            }
        }
    }
}

@Composable
private fun shouldUseDarkTheme(
    uiState: MainActivityUiState,
): Boolean = when (uiState) {
    Loading -> isSystemInDarkTheme()
    is Success -> when (uiState.appEnvData.themeConfig) {
        ThemeConfig.FOLLOW_SYSTEM -> isSystemInDarkTheme()
        ThemeConfig.LIGHT -> false
        ThemeConfig.DARK -> true
    }
}

@Composable
private fun shouldUseLocalization(
    uiState: MainActivityUiState,
): Locale {
    val context = LocalContext.current
    val resources = context.resources
    val config = resources.configuration

    val systemLocale = Locale.getDefault()

    val locale = when (uiState) {
        Loading -> systemLocale

        is Success -> {
            when (val localeConfig = uiState.appEnvData.localizationConfig) {
                LocalizationConfig.FOLLOW_SYSTEM -> systemLocale
                else -> Locale.forLanguageTag(localeConfig.languageCode)
            }
        }
    }

    if (config.locales[0] != locale) {
        config.setLocale(locale)
        val updatedContext = context.createConfigurationContext(config)
        resources.updateConfiguration(config, updatedContext.resources.displayMetrics)
    }

    return locale
}
