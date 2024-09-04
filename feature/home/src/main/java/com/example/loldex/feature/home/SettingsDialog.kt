package com.example.loldex.feature.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.loldex.core.designsystem.component.DefaultAlertDialog
import com.example.loldex.core.designsystem.component.LdButton
import com.example.loldex.core.designsystem.theme.Gray800
import com.example.loldex.core.designsystem.theme.ldTypography

@Composable
fun SettingsDialog(
    onDismiss: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {

    val settingsUiState by viewModel.settingsUiState.collectAsStateWithLifecycle()
    SettingsContent(
        onDismiss = onDismiss,
        settingsUiState = settingsUiState,
    )
}

@Composable
fun SettingsContent(
    onDismiss: () -> Unit,
    settingsUiState: SettingsUiState,
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
            HorizontalDivider()
            Text(
                modifier = Modifier.padding(vertical = 16.dp),
                text = stringResource(id = R.string.settings_dialog_dark_mode_theme),
                style = MaterialTheme.ldTypography.fontTitleS.copy(fontWeight = FontWeight.Bold),
            )
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
