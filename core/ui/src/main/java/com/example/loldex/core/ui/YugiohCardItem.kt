package com.example.loldex.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.loldex.core.designsystem.component.AsyncImageView
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.designsystem.theme.ldTypography
import com.example.loldex.core.model.YugiohCardData

@Composable
fun YugiohCardItem(
    onClickedItem: (String) -> Unit,
    yugiohCardData: YugiohCardData
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = { onClickedItem(yugiohCardData.name) },
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
                    .clip(RoundedCornerShape(8.dp)),
                url = yugiohCardData.cardImages[0].imageUrlSmall,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                placeholderContent = {
                    SkeletonBox(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(2f / 3f)
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
fun YugiohCardItemPreview(
    @PreviewParameter(YugiohCardDataPreviewParameterProvider::class) yugiohCardList: List<YugiohCardData>
) {
    val yugiohCardData = yugiohCardList[0]
    YugiohCardItem(
        onClickedItem = {},
        yugiohCardData = yugiohCardData
    )
}