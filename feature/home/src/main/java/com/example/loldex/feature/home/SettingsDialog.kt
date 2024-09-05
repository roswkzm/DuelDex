package com.example.loldex.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.loldex.core.designsystem.theme.Gray800
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.designsystem.theme.ldTypography
import com.example.loldex.core.model.UserEnvData
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
        onChangeThemeConfig = viewModel::setThemeConfig
    )
}

@Composable
fun SettingsContent(
    onDismiss: () -> Unit,
    settingsUiState: SettingsUiState,
    onChangeThemeConfig: (ThemeConfig) -> Unit,
) {
    DefaultAlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(id = R.string.settings_dialog_title),
                style = MaterialTheme.ldTypography.fontTitleS.copy(fontWeight = FontWeight.Bold),
            )
        },
        text = {
            when (settingsUiState) {
                SettingsUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier)
                }

                is SettingsUiState.Success -> {
                    var selectedThemeConfig = settingsUiState.appEnvData.themeConfig
                    Column {
                        HorizontalDivider()
                        Text(
                            modifier = Modifier.padding(vertical = 16.dp),
                            text = stringResource(id = R.string.settings_dialog_dark_mode_theme),
                            style = MaterialTheme.ldTypography.fontTitleS.copy(fontWeight = FontWeight.Bold),
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
                    }
                }
            }
        },
        confirmButton = {
            LdButton(
                modifier = Modifier,
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Gray800,
                    contentColor = White
                ),
                text = {
                    Text(
                        text = stringResource(id = R.string.settings_dialog_btn_confirm),
                        style = MaterialTheme.ldTypography.fontLabelM
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
            .padding(12.dp)
    ) {
        RadioButton(
            selected = selected,
            onClick = null
        )
        Spacer(Modifier.width(8.dp))
        Text(text)
    }
}

@ThemePreviews
@Composable
fun SettingsContentPreview() {
    val settingsUiState = SettingsUiState.Success(
        UserEnvData(
            themeConfig = ThemeConfig.FOLLOW_SYSTEM
        )
    )
    SettingsContent(
        onDismiss = {},
        settingsUiState = settingsUiState,
        onChangeThemeConfig = {},
    )
}