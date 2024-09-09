package com.example.loldex.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.loldex.core.designsystem.component.LdButton
import com.example.loldex.core.designsystem.theme.LolDexTheme
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.designsystem.theme.ldTypography

@Composable
fun UiStateFailedScreen(
    onClickedRetry: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = Icons.Filled.Error,
            contentDescription = "Error Image",
            modifier = Modifier.size(128.dp),
            colorFilter = ColorFilter.tint(Color.Red)
        )

        Text(
            text = stringResource(id = R.string.ui_state_failed_title),
            style = MaterialTheme.ldTypography.fontBodyL,
            color = Color.Red,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        LdButton(
            onClick = onClickedRetry,
            modifier = Modifier.padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onSurfaceVariant),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.ui_state_failed_btn_retry),
                style = MaterialTheme.ldTypography.fontBodyL,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
    }
}

@ThemePreviews
@Composable
fun UiStateFailedScreenPreview() {
    LolDexTheme {
        UiStateFailedScreen(
            onClickedRetry = {},
        )
    }
}