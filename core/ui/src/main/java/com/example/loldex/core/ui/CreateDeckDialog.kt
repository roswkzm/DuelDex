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
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loldex.core.designsystem.component.DefaultAlertDialog
import com.example.loldex.core.designsystem.component.LdButton
import com.example.loldex.core.designsystem.theme.AttributeFire
import com.example.loldex.core.designsystem.theme.Error
import com.example.loldex.core.designsystem.theme.Gray800
import com.example.loldex.core.designsystem.theme.Text0
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.designsystem.theme.ldTypography

@Composable
fun CreateDeckDialog(
    onDismiss: () -> Unit,
    onClickedConfirm: (String) -> Unit,
    inputValue: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
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
                isError = isError,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.insert_deck_dialog_placeholder),
                        style = MaterialTheme.ldTypography.fontLabelM,
                        color = Gray
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
                                tint = Error
                            )
                            Text(
                                text = stringResource(id = errorMessage),
                                style = MaterialTheme.ldTypography.fontBodyM.copy(
                                    fontSize = 12.sp,
                                    lineHeight = 18.sp,
                                    fontWeight = FontWeight(400)
                                ),
                                color = Error
                            )
                        }
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    errorTextColor = Error,
                    errorBorderColor = Error,
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
        isError = false
    )
}