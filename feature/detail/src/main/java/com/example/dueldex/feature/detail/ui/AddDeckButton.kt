package com.example.dueldex.feature.detail.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dueldex.core.designsystem.component.DdButton
import com.example.dueldex.core.designsystem.theme.DuelDexTheme
import com.example.dueldex.core.designsystem.theme.ThemePreviews
import com.example.dueldex.core.designsystem.theme.ddTypography
import com.example.dueldex.feature.detail.R

@Composable
internal fun AddDeckButton(
    onClickedSavedCard: () -> Unit
) {
    DdButton(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onClickedSavedCard,
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onSurfaceVariant),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 5.dp),
        leadingIcon = {
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = Icons.Filled.Add,
                contentDescription = "Add Deck",
                tint = MaterialTheme.colorScheme.onSecondary
            )
        },
        text = {
            Text(
                text = stringResource(id = R.string.add_deck_btn_text),
                style = MaterialTheme.ddTypography.fontBodyS,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
    )
}

@ThemePreviews
@Composable
fun AddDeckButtonPreview() {
    DuelDexTheme {
        AddDeckButton(
            onClickedSavedCard = {}
        )
    }
}