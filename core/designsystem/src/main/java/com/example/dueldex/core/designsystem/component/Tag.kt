package com.example.dueldex.core.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dueldex.core.designsystem.theme.DuelDexTheme
import com.example.dueldex.core.designsystem.theme.Neutral20
import com.example.dueldex.core.designsystem.theme.ThemePreviews
import com.example.dueldex.core.designsystem.theme.ddTypography
import com.example.dueldex.core.designsystem.R

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
            style = MaterialTheme.ddTypography.fontLabelM,
            color = Color.White
        )
    }
}

@Composable
fun RectangleTag(
    name: String,
    containerColor: Color,
    contentColor: Color,
    onClickedTag: (String) -> Unit
) {
    Button(
        onClick = { onClickedTag(name) },
        enabled = true,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 5.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.ddTypography.fontBodyM,
            color = contentColor
        )
    }
}

@Composable
fun TagWithDeleteButton(
    name: String,
    color: Color,
    contentColor: Color,
    onClickedTag: (String) -> Unit,
    onClickedDelete: (String) -> Unit,
) {
    Button(
        onClick = { onClickedTag(name) },
        enabled = true,
        colors = ButtonDefaults.buttonColors(color),
        shape = RoundedCornerShape(60.dp),
        border = BorderStroke(1.dp, contentColor),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 5.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.ddTypography.fontBodyM,
            color = contentColor
        )
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
            modifier = Modifier
                .size(18.dp)
                .clickable { onClickedDelete(name) },
            imageVector = Icons.Filled.Close,
            contentDescription = null,
            tint = contentColor
        )
    }
}

@Composable
fun TagButton(
    name: String,
    color: Color,
    contentColor: Color,
    icon: ImageVector,
    onClickedTag: (String) -> Unit,
) {
    Button(
        onClick = { onClickedTag(name) },
        enabled = true,
        colors = ButtonDefaults.buttonColors(color),
        shape = RoundedCornerShape(60.dp),
        border = BorderStroke(1.dp, Neutral20),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 5.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.ddTypography.fontBodyM,
            color = contentColor
        )
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
            modifier = Modifier
                .size(18.dp),
            imageVector = icon,
            contentDescription = null,
            tint = contentColor
        )
    }
}

@ThemePreviews
@Composable
fun SimpleTagPreview() {
    DuelDexTheme {
        SimpleTag(
            color = Color.Magenta,
            painterResource = R.drawable.image,
            title = "Yh-Gi-Oh",
            onClickedTag = {}
        )
    }
}

@ThemePreviews
@Composable
fun RectangleTagPreview() {
    DuelDexTheme {
        RectangleTag(
            name = "Yh-Gi-Oh",
            containerColor = MaterialTheme.colorScheme.onSurfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSecondary,
            onClickedTag = {}
        )
    }
}

@ThemePreviews
@Composable
fun TagWithDeleteButtonPreview() {
    DuelDexTheme {
        TagWithDeleteButton(
            name = "Yh-Gi-Oh",
            color = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            onClickedTag = {},
            onClickedDelete = {}
        )
    }
}

@ThemePreviews
@Composable
fun TagButtonPreview() {
    DuelDexTheme {
        TagButton(
            name = "Yh-Gi-Oh",
            color = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            icon = Icons.Filled.KeyboardArrowDown,
            onClickedTag = {}
        )
    }
}