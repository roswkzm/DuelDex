package com.example.dueldex.core.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.dueldex.core.designsystem.theme.ThemePreviews
import com.example.dueldex.core.designsystem.theme.ddTypography

@Composable
fun DefaultAlertDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    containerColor: Color = White,
    title: @Composable (() -> Unit)? = null,
    text: @Composable (() -> Unit)? = null,
    confirmButton: @Composable (() -> Unit),
    dismissButton: @Composable (() -> Unit)? = null,
    properties: DialogProperties = DialogProperties()
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        containerColor = containerColor,
        title = title,
        text = text,
        confirmButton = confirmButton,
        dismissButton = dismissButton,
        shape = MaterialTheme.shapes.medium,
        properties = properties
    )
}

@ThemePreviews
@Composable
fun ModalAlertDialogPreview() {
    DefaultAlertDialog(
        onDismissRequest = { /*TODO*/ },
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Title",
                    style = MaterialTheme.ddTypography.fontTitleS.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.sizeIn(maxWidth = 24.dp, maxHeight = 24.dp),
                    colors = IconButtonDefaults.iconButtonColors(contentColor = Black)
                ) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = null)
                }
            }
        },
        text = {
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Et ultricies etiam habitasse augue.",
                style = MaterialTheme.ddTypography.fontBodyM
            )
        },
        confirmButton = {
            DdButton(
                modifier = Modifier,
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Black,
                    contentColor = White
                ),
                text = { Text("confirm") },
            )
        },
        dismissButton = {
            DdButton(
                modifier = Modifier,
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = White,
                    contentColor = LightGray
                ),
                text = { Text("dismiss") },
            )
        }
    )
}

@ThemePreviews
@Composable
fun NotificationAlertDialogPreview() {
    DefaultAlertDialog(
        onDismissRequest = { /*TODO*/ },
        title = {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Filled.Warning,
                    contentDescription = null,
                    modifier = Modifier.sizeIn(maxWidth = 20.dp, maxHeight = 20.dp),
                    tint = Red
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = "업로드 중 서버와의 연결에 실패하였습니다.",
                    style = MaterialTheme.ddTypography.fontBodyM.copy(color = Red),
                )
            }
        },
        text = {
            Text(
                text = "* 업로드가 중단된 영상은 메인 화면에서 추가 업로드를 통해 다시 업로드 할 수 있습니다. ",
                style = MaterialTheme.ddTypography.fontBodyM
            )
        },
        confirmButton = {
            DdButton(
                modifier = Modifier,
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Black,
                    contentColor = White
                ),
                text = { Text("dismissButton") },
            )
        }
    )
}