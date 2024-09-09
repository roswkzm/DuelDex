package com.example.loldex.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loldex.core.designsystem.component.DefaultAlertDialog
import com.example.loldex.core.designsystem.component.LdButton
import com.example.loldex.core.designsystem.theme.LolDexTheme
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.designsystem.theme.ldTypography

@Composable
fun CreateDeckDialog(
    onDismiss: () -> Unit,
    containerColor: Color,
    onClickedConfirm: (String) -> Unit,
    inputValue: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
) {
    DefaultAlertDialog(
        onDismissRequest = onDismiss,
        containerColor = containerColor,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.insert_deck_dialog_title),
                    style = MaterialTheme.ldTypography.fontTitleS.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier.sizeIn(maxWidth = 24.dp, maxHeight = 24.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = null
                    )
                }
            }
        },
        text = {
            OutlinedTextField(
                value = inputValue,
                onValueChange = onValueChange,
                isError = isError,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.insert_deck_dialog_placeholder),
                        style = MaterialTheme.ldTypography.fontLabelM,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                    )
                },
                supportingText = {
                    if (isError) {
                        val errorMessage = if (inputValue.isEmpty()) {
                            R.string.insert_deck_dialog_empty_deck_name_error
                        } else {
                            R.string.insert_deck_dialog_duplicate_deck_name_error
                        }

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(16.dp),
                                painter = painterResource(id = R.drawable.error_circle),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.error
                            )
                            Text(
                                text = stringResource(id = errorMessage),
                                style = MaterialTheme.ldTypography.fontBodyM.copy(
                                    fontSize = 12.sp,
                                    lineHeight = 18.sp,
                                    fontWeight = FontWeight(400)
                                ),
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    focusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    errorTextColor = MaterialTheme.colorScheme.error,
                    errorBorderColor = MaterialTheme.colorScheme.error,
                ),
            )
        },
        confirmButton = {
            LdButton(
                modifier = Modifier,
                onClick = {
                    if (!isError) {
                        onClickedConfirm(inputValue)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
                text = {
                    Text(
                        text = stringResource(id = R.string.insert_deck_dialog_btn_confirm),
                        style = MaterialTheme.ldTypography.fontLabelM,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                },
            )
        },
        dismissButton = {
            LdButton(
                modifier = Modifier,
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onSecondary
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
    LolDexTheme {
        CreateDeckDialog(
            onDismiss = {},
            containerColor = MaterialTheme.colorScheme.surfaceBright,
            onClickedConfirm = {},
            inputValue = "",
            onValueChange = {},
            isError = false
        )
    }
}