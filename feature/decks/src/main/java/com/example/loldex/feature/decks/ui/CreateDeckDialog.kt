package com.example.loldex.feature.decks.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.loldex.core.designsystem.component.DefaultAlertDialog
import com.example.loldex.core.designsystem.component.LdButton
import com.example.loldex.core.designsystem.theme.AttributeFire
import com.example.loldex.core.designsystem.theme.Gray800
import com.example.loldex.core.designsystem.theme.Text0
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.designsystem.theme.ldTypography
import com.example.loldex.feature.decks.R

@Composable
fun CreateDeckDialog(
    onDismiss: () -> Unit,
    onClickedConfirm: (String) -> Unit,
    inputValue: String,
    onValueChange: (String) -> Unit,
) {
    DefaultAlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.insert_deck_dialog_title),
                    style = MaterialTheme.ldTypography.fontTitleS.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier.sizeIn(maxWidth = 24.dp, maxHeight = 24.dp),
                    colors = IconButtonDefaults.iconButtonColors(contentColor = Black)
                ) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = null)
                }
            }
        },
        text = {
            OutlinedTextField(
                value = inputValue,
                onValueChange = onValueChange,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.insert_deck_dialog_placeholder),
                        style = MaterialTheme.ldTypography.fontLabelM,
                        color = Gray
                    )
                }
            )
        },
        confirmButton = {
            LdButton(
                modifier = Modifier,
                onClick = {
                    onClickedConfirm(inputValue)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Gray800,
                    contentColor = White
                ),
                text = {
                    Text(
                        text = stringResource(id = R.string.insert_deck_dialog_btn_confirm),
                        style = MaterialTheme.ldTypography.fontLabelM
                    )
                },
            )
        },
        dismissButton = {
            LdButton(
                modifier = Modifier,
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(
                    containerColor = AttributeFire,
                    contentColor = Text0
                ),
                text = {
                    Text(
                        text = stringResource(id = R.string.insert_deck_dialog_btn_cancel),
                        style = MaterialTheme.ldTypography.fontLabelM
                    )
                },
            )
        }
    )
}

@ThemePreviews
@Composable
internal fun CreateDeckDialogPreview() {
    CreateDeckDialog(
        onDismiss = {},
        onClickedConfirm = {},
        inputValue = "",
        onValueChange = {},
    )
}