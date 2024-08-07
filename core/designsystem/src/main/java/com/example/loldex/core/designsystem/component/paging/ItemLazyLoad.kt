package com.example.loldex.core.designsystem.component.paging

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.loldex.core.designsystem.R

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
        modifier = modifier.padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            color = Color.Red,
            modifier = Modifier.weight(1f),
            maxLines = 2
        )
        OutlinedButton(onClick = onClickRetry) {
            Text(text = stringResource(id = R.string.core_designsystem_paging_state_error_retry))
        }
    }
}

@Preview
@Composable
fun PageLoaderPreview() {
    PageLoader(
        modifier = Modifier.size(24.dp),
        color = Color.Blue,
        strokeWidth = 4.dp
    )
}

@Preview
@Composable
fun ErrorMessagePreview() {
    ErrorMessage(
        modifier = Modifier.fillMaxWidth(),
        message = "Error Message",
        onClickRetry = {}
    )
}