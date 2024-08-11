package com.example.loldex.feature.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.loldex.core.designsystem.component.LdBackGround
import com.example.loldex.core.designsystem.component.SimpleTag
import com.example.loldex.core.designsystem.theme.LolDexTheme
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.designsystem.theme.ldTypography
import com.example.loldex.core.model.YugiohCardData
import com.example.loldex.core.ui.YugiohCardDataPreviewParameterProvider
import com.example.loldex.core.ui.attribute.AttributeTag
import com.example.loldex.feature.detail.ui.AttackDefensePowerLayout
import com.example.loldex.core.designsystem.R as DesignR

@Composable
internal fun CardDetailRoute(
    viewModel: CardDetailViewModel = hiltViewModel(),
) {
    val cardDetailUiState by viewModel.cardDetailUiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    CardDetailScreen(
        cardDetailUiState = cardDetailUiState,
        scrollState = scrollState,
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun CardDetailScreen(
    cardDetailUiState: CardDetailUiState, scrollState: ScrollState
) {
    when (cardDetailUiState) {
        CardDetailUiState.Error -> {
            Text(text = "CardDetailUiState.Error")
        }

        CardDetailUiState.Loading -> {
            Text(text = "CardDetailUiState.Loading")
        }

        is CardDetailUiState.Success -> {
            val yugiohCardData = cardDetailUiState.yugiohCardDat
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
//                LazyRow(
//                    modifier = Modifier
//                ) {
//                    items(yugiohCardData.cardImages.size) { index ->
//                        AsyncImageView(modifier = Modifier.fillMaxWidth()
//                            .clip(RoundedCornerShape(8.dp)),
//                            url = yugiohCardData.cardImages[index].imageUrl,
//                            contentDescription = null,
//                            contentScale = ContentScale.Fit,
//                            placeholderContent = {
//                                SkeletonBox(
//                                    modifier = Modifier.fillMaxWidth().aspectRatio(2f / 3f)
//                                )
//                            })
//                    }
//                }

                Column(
                    modifier = Modifier
                        .padding(horizontal = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    yugiohCardData.level?.let {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            repeat(it) {
                                Image(
                                    modifier = Modifier.size(20.dp),
                                    painter = painterResource(id = DesignR.drawable.level_star),
                                    contentDescription = "Level Icon",
                                )
                            }
                        }
                    }

                    Text(
                        text = yugiohCardData.name,
                        style = MaterialTheme.ldTypography.fontTitleL,
                        color = Color.Black
                    )

                    AttackDefensePowerLayout(yugiohCardData.atk, yugiohCardData.def)

                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        yugiohCardData.attribute?.let {
                            AttributeTag(
                                modifier = Modifier,
                                attribute = it,
                                onClickedAttribute = {}
                            )
                        }

                        SimpleTag(
                            color = Color.Magenta,
                            title = yugiohCardData.race,
                            onClickedTag = {}
                        )

                        SimpleTag(
                            color = Color.Blue,
                            title = yugiohCardData.type,
                            onClickedTag = {}
                        )

                        SimpleTag(
                            color = Color.Cyan,
                            title = yugiohCardData.frameType,
                            onClickedTag = {}
                        )

                        yugiohCardData.archetype?.let {
                            SimpleTag(
                                color = Color.DarkGray,
                                title = it,
                                onClickedTag = {}
                            )
                        }
                    }

                    Text(
                        text = yugiohCardData.desc,
                        style = MaterialTheme.ldTypography.fontBodyL,
                        color = Color.Black
                    )
                }
            }
        }
    }
}


@ThemePreviews
@Composable
fun CardDetailScreenPreview(
    @PreviewParameter(YugiohCardDataPreviewParameterProvider::class) yugiohCardList: List<YugiohCardData>
) {
    val scrollState = rememberScrollState()
    val yugiohCardData = yugiohCardList[0]
    LolDexTheme {
        LdBackGround {
            CardDetailScreen(
                cardDetailUiState = CardDetailUiState.Success(yugiohCardData),
                scrollState = scrollState
            )
        }
    }
}