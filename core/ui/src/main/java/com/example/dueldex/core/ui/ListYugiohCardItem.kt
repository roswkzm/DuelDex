package com.example.dueldex.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.dueldex.core.designsystem.R
import com.example.dueldex.core.designsystem.component.AsyncImageView
import com.example.dueldex.core.designsystem.component.SimpleTag
import com.example.dueldex.core.designsystem.theme.DuelDexTheme
import com.example.dueldex.core.designsystem.theme.ThemePreviews
import com.example.dueldex.core.designsystem.theme.ddTypography
import com.example.dueldex.core.model.YugiohCardData
import com.example.dueldex.core.ui.attribute.AttributeSize
import com.example.dueldex.core.ui.attribute.AttributeTag
import com.example.dueldex.core.ui.preview_parameter_provider.YugiohCardDataPreviewParameterProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListYugiohCardItem(
    onClickedItem: (String) -> Unit,
    yugiohCardData: YugiohCardData,
    onSwipedDeleteEvent: (Long) -> Unit,
) {
    val density = LocalDensity.current
    val scrollState = rememberScrollState()
    var cardContentHeight by remember { mutableStateOf(0.dp) }

    CustomSwipeToDismissBox(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundContent = { swipeDismissState ->
            DefaultSwipeToDismissBackgroundBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardContentHeight)
                    .clip(RoundedCornerShape(8.dp)),
                swipeDismissState = swipeDismissState
            )
        },
        enableDismissFromStartToEnd = false,
        enableDismissFromEndToStart = true,
        onEndToStartEvent = {
            onSwipedDeleteEvent(yugiohCardData.id)
        },
        content = {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        cardContentHeight = with(density) { coordinates.size.height.toDp() }
                    },
                onClick = { onClickedItem(yugiohCardData.name) },
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(),
                elevation = CardDefaults.elevatedCardElevation(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        AsyncImageView(
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            url = yugiohCardData.cardImages[0].imageUrlCropped,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            placeholderContent = {
                                SkeletonBox(
                                    modifier = Modifier
                                        .size(100.dp)
                                )
                            }
                        )

                        LevelNameDescriptionLayout(
                            yugiohCardData = yugiohCardData
                        )
                    }

                    AttackDefensePowerLayout(
                        yugiohCardData = yugiohCardData
                    )

                    CardAttributeLayout(
                        scrollState = scrollState,
                        yugiohCardData = yugiohCardData
                    )
                }
            }
        }
    )
}

@Composable
private fun LevelNameDescriptionLayout(
    yugiohCardData: YugiohCardData
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        yugiohCardData.level?.let {
            Row(
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                repeat(it) {
                    Image(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.level_star),
                        contentDescription = "Level Icon",
                    )
                }
            }
        }

        Text(
            text = yugiohCardData.name,
            style = MaterialTheme.ddTypography.fontTitleS,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = yugiohCardData.desc,
            style = MaterialTheme.ddTypography.fontTitleXS,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
            maxLines = if (yugiohCardData.level == null) 3 else 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun AttackDefensePowerLayout(
    yugiohCardData: YugiohCardData
) {
    Row(
        modifier = Modifier
            .padding(top = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        yugiohCardData.atk?.let {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(22.dp),
                    painter = painterResource(id = R.drawable.atk),
                    contentDescription = "Attack Power",
                )

                Text(
                    text = "$it",
                    style = MaterialTheme.ddTypography.fontTitleXS,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        yugiohCardData.def?.let {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(22.dp),
                    painter = painterResource(id = R.drawable.def),
                    contentDescription = "Defense Power",
                )

                Text(
                    text = "$it",
                    style = MaterialTheme.ddTypography.fontTitleXS,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun CardAttributeLayout(
    scrollState: ScrollState,
    yugiohCardData: YugiohCardData,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        yugiohCardData.attribute?.let {
            AttributeTag(
                modifier = Modifier.height(30.dp),
                attribute = it,
                attributeSize = AttributeSize.S,
                onClickedAttribute = {}
            )
        }

        SimpleTag(
            modifier = Modifier.height(30.dp),
            color = Color.Magenta,
            title = yugiohCardData.race,
            onClickedTag = {}
        )

        SimpleTag(
            modifier = Modifier.height(30.dp),
            color = Color.Blue,
            title = yugiohCardData.type,
            onClickedTag = {}
        )

        SimpleTag(
            modifier = Modifier.height(30.dp),
            color = Color.Cyan,
            title = yugiohCardData.frameType,
            onClickedTag = {}
        )

        yugiohCardData.archetype?.let {
            SimpleTag(
                modifier = Modifier.height(30.dp),
                color = Color.DarkGray,
                title = it,
                onClickedTag = {}
            )
        }
    }
}

@ThemePreviews
@Composable
fun ListYugiohCardItemPreview(
    @PreviewParameter(YugiohCardDataPreviewParameterProvider::class) yugiohCardList: List<YugiohCardData>
) {
    DuelDexTheme {
        val yugiohCardData = yugiohCardList[0]
        ListYugiohCardItem(
            onClickedItem = {},
            yugiohCardData = yugiohCardData,
            onSwipedDeleteEvent = {}
        )
    }
}