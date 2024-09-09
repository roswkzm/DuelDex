package com.example.loldex.core.designsystem.component.paging

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.loldex.core.designsystem.R
import com.example.loldex.core.designsystem.component.LdButton
import com.example.loldex.core.designsystem.theme.LolDexTheme
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.designsystem.theme.ldTypography

@Composable
fun PageLoader(
    modifier: Modifier = Modifier,
    color: Color,
    strokeWidth: Dp = 4.dp

) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = modifier,
            color = color,
            strokeWidth = strokeWidth
        )

    }
}

// Paging Data 계속 Load시 하단에 보이는 ProgressBar
@Composable
fun LoadingNextPageItem(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

// 데이터를 못가져올 시 UI
@Composable
fun ErrorMessage(
    modifier: Modifier = Modifier,
    message: String,
    onClickRetry: () -> Unit
) {
    Row(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.weight(1f),
            maxLines = 2
        )
        LdButton(
            modifier = Modifier,
            onClick = onClickRetry,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onSurfaceVariant,
                contentColor = White
            ),
            text = {
                Text(
                    text = stringResource(id = R.string.core_designsystem_paging_state_error_retry),
                    style = MaterialTheme.ldTypography.fontLabelM,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            },
        )
    }
}

@ThemePreviews
@Composable
fun PageLoaderPreview() {
    LolDexTheme {
        PageLoader(
            modifier = Modifier.size(24.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            strokeWidth = 4.dp
        )
    }
}

@ThemePreviews
@Composable
fun ErrorMessagePreview() {
    LolDexTheme {
        ErrorMessage(
            modifier = Modifier.fillMaxWidth(),
            message = "Error Message",
            onClickRetry = {}
        )
    }
}