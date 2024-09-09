package com.example.loldex.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.loldex.core.designsystem.component.DefaultAlertDialog
import com.example.loldex.core.designsystem.component.LdButton
import com.example.loldex.core.designsystem.theme.LolDexTheme
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.designsystem.theme.ldTypography
import com.example.loldex.core.model.UserEnvData
import com.example.loldex.core.model.enums.LocalizationConfig
import com.example.loldex.core.model.enums.ThemeConfig

@Composable
fun SettingsDialog(
    onDismiss: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val settingsUiState by viewModel.settingsUiState.collectAsStateWithLifecycle()
    SettingsContent(
        onDismiss = onDismiss,
        settingsUiState = settingsUiState,
        onChangeThemeConfig = viewModel::setThemeConfig,
        onChangeLocalizationConfig = viewModel::setLocalizationConfig,
    )
}

@Composable
fun SettingsContent(
    onDismiss: () -> Unit,
    settingsUiState: SettingsUiState,
    onChangeThemeConfig: (ThemeConfig) -> Unit,
    onChangeLocalizationConfig: (LocalizationConfig) -> Unit,
) {
    DefaultAlertDialog(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.surfaceBright,
        title = {
            Text(
                text = stringResource(id = R.string.settings_dialog_title),
                style = MaterialTheme.ldTypography.fontTitleS.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        text = {
            when (settingsUiState) {
                SettingsUiState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                is SettingsUiState.Success -> {
                    val selectedThemeConfig = settingsUiState.appEnvData.themeConfig
                    val selectedLocalizationConfig = settingsUiState.appEnvData.localizationConfig
                    Column {
                        HorizontalDivider()
                        Text(
                            modifier = Modifier.padding(vertical = 16.dp),
                            text = stringResource(id = R.string.settings_dialog_dark_mode_theme),
                            style = MaterialTheme.ldTypography.fontTitleS.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Column(
                            modifier = Modifier
                        ) {
                            for (theme in ThemeConfig.entries) {
                                ThemeRadioButton(
                                    text = theme.name,
                                    selected = selectedThemeConfig == theme,
                                    onClick = { onChangeThemeConfig(theme) }
                                )
                            }
                        }

                        Text(
                            modifier = Modifier.padding(vertical = 16.dp),
                            text = stringResource(id = R.string.settings_dialog_localization_mode),
                            style = MaterialTheme.ldTypography.fontTitleS.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Column(
                            modifier = Modifier
                        ) {
                            for (localization in LocalizationConfig.entries) {
                                ThemeRadioButton(
                                    text = localization.name,
                                    selected = selectedLocalizationConfig == localization,
                                    onClick = { onChangeLocalizationConfig(localization) }
                                )
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            LdButton(
                modifier = Modifier,
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    contentColor = White
                ),
                text = {
                    Text(
                        text = stringResource(id = R.string.settings_dialog_btn_confirm),
                        style = MaterialTheme.ldTypography.fontLabelM,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                },
            )
        }
    )
}

@Composable
fun ThemeRadioButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .selectable(
                selected = selected,
                role = Role.RadioButton,
                onClick = onClick
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = null,
            colors = RadioButtonDefaults.colors(MaterialTheme.colorScheme.onSurfaceVariant)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.ldTypography.fontLabelM,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@ThemePreviews
@Composable
fun SettingsContentPreview() {
    val settingsUiState = SettingsUiState.Success(
        UserEnvData(
            themeConfig = ThemeConfig.FOLLOW_SYSTEM,
            localizationConfig = LocalizationConfig.FOLLOW_SYSTEM,
        )
    )
    LolDexTheme {
        SettingsContent(
            onDismiss = {},
            settingsUiState = settingsUiState,
            onChangeThemeConfig = {},
            onChangeLocalizationConfig = {},
        )
    }
}