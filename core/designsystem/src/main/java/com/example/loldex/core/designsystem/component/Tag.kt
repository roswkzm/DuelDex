package com.example.loldex.core.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.loldex.core.designsystem.R
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.designsystem.theme.ldTypography

@Composable
fun SimpleTag(
    modifier: Modifier = Modifier,
    color: Color,
    @DrawableRes painterResource: Int? = null,
    title: String,
    onClickedTag: (String) -> Unit
) {
    Button(
        modifier = modifier,
        onClick = { onClickedTag(title) },
        colors = ButtonDefaults.buttonColors(color),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 5.dp)
    ) {
        painterResource?.let {
            Image(
                modifier = Modifier
                    .size(28.dp),
                painter = painterResource(id = it),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
        Text(
            text = title,
            style = MaterialTheme.ldTypography.fontLabelM,
            color = Color.White
        )
    }
}

@ThemePreviews
@Composable
fun SimpleTagPreview() {
    SimpleTag(
        color = Color.Magenta,
        painterResource = R.drawable.image,
        title = "Simple Tag",
        onClickedTag = {}
    )
}