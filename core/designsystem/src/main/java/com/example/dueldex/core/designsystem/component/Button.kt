package com.example.dueldex.core.designsystem.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.dueldex.core.designsystem.icon.DuelDexIcons
import com.example.dueldex.core.designsystem.theme.DuelDexTheme
import com.example.dueldex.core.designsystem.theme.ThemePreviews

@Composable
fun DdButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    colors: ButtonColors? = null,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(horizontal = 8.dp, vertical = 5.dp),
    leadingIcon: @Composable (() -> Unit)? = null,
    text: @Composable () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        enabled = enabled,
        colors = colors ?: ButtonDefaults.buttonColors(),
        contentPadding = contentPadding,
        content = {
            ButtonContent(
                leadingIcon = leadingIcon,
                text = text
            )
        },
    )
}

@Composable
fun RowScope.ButtonContent(
    leadingIcon: @Composable (() -> Unit)? = null,
    text: @Composable () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (leadingIcon != null) {
            leadingIcon()
        }
        text()
    }
}

@ThemePreviews
@Composable
fun MwIconButtonPreview() {
    DuelDexTheme {
        DdButton(
            modifier = Modifier,
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            leadingIcon = { Icon(imageVector = DuelDexIcons.Add, contentDescription = null) },
            text = { Text("Test button") },
        )
    }
}

@ThemePreviews
@Composable
fun MwButtonPreview() {
    DuelDexTheme {
        DdButton(
            modifier = Modifier,
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            text = { Text("Test button") },
        )
    }
}