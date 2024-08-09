package com.example.loldex.feature.detail

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.loldex.core.designsystem.component.AsyncImageView
import com.example.loldex.core.designsystem.component.LdBackGround
import com.example.loldex.core.designsystem.theme.LolDexTheme
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.model.YugiohCardData
import com.example.loldex.core.ui.SkeletonBox
import com.example.loldex.core.ui.YugiohCardDataPreviewParameterProvider

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

@Composable
internal fun CardDetailScreen(
    cardDetailUiState: CardDetailUiState,
    scrollState: ScrollState
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
                LazyRow(
                    modifier = Modifier
                ) {
                    items(yugiohCardData.cardImages.size) { index ->
                        AsyncImageView(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp)),
                            url = yugiohCardData.cardImages[index].imageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            placeholderContent = {
                                SkeletonBox(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(2f / 3f)
                                )
                            }
                        )
                    }
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
