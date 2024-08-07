package com.example.loldex.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.loldex.core.designsystem.component.AsyncImageView
import com.example.loldex.core.designsystem.theme.Neutral30
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.designsystem.theme.ldTypography
import com.example.loldex.core.model.YugiohCardData

@Composable
fun YugiohCardItem(
    onClickedItem: (Long) -> Unit,
    yugiohCardData: YugiohCardData
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = { onClickedItem(yugiohCardData.id) },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(),
        elevation = CardDefaults.elevatedCardElevation(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImageView(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onClickedItem(yugiohCardData.id) },
                url = yugiohCardData.cardImages[0].imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                placeholderContent = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Neutral30)
                    )
                }
            )

            Text(
                modifier = Modifier.padding(6.dp),
                text = yugiohCardData.name,
                style = MaterialTheme.ldTypography.fontTitleXS,
                color = Color.White,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@ThemePreviews
@Composable
fun YugiohCardItemPreview() {
}