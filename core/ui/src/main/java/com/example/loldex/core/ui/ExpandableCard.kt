package com.example.loldex.core.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.designsystem.theme.ldTypography

@Composable
fun ExpandableCard(
    modifier: Modifier,
    shape: Shape = CardDefaults.shape,
    color: Color = MaterialTheme.colorScheme.surface,
    elevation: Dp = 4.dp,
    title: String,
    titleColor: Color,
    content: @Composable () -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
            .clickable { isExpanded = !isExpanded },
        shape = shape,
        colors = CardDefaults.cardColors(color),
        elevation = CardDefaults.cardElevation(elevation)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            ExpandableCardTitle(
                isExpanded = isExpanded,
                title = title,
                titleColor = titleColor,
            )

            AnimatedVisibility(
                modifier = Modifier
                    .fillMaxWidth(),
                visible = isExpanded
            ) {
                content()
            }
        }
    }
}

@Composable
fun ExpandableCardTitle(
    isExpanded: Boolean,
    title: String,
    titleColor: Color,
) {
    val icon =
        if (isExpanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.ldTypography.fontTitleM,
            color = titleColor
        )

        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(30.dp),
            tint = titleColor
        )
    }
}

@ThemePreviews
@Composable
fun ExpandableCardPreview() {
    ExpandableCard(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Color.Gray,
        elevation = 4.dp,
        title = "Title",
        titleColor = Color.Blue,
        content = {
            Column {
                Text(text = "123")
                Text(text = "456")
                Text(text = "789")
            }
        }
    )
}
