package com.example.dueldex.feature.detail

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dueldex.core.designsystem.component.DdBackGround
import com.example.dueldex.core.designsystem.component.SimpleTag
import com.example.dueldex.core.designsystem.theme.DuelDexTheme
import com.example.dueldex.core.designsystem.theme.ThemePreviews
import com.example.dueldex.core.designsystem.theme.ddTypography
import com.example.dueldex.core.model.YugiohCardData
import com.example.dueldex.core.ui.CarouselPager
import com.example.dueldex.core.ui.UiStateFailedScreen
import com.example.dueldex.core.ui.attribute.AttributeSize
import com.example.dueldex.core.ui.attribute.AttributeTag
import com.example.dueldex.core.ui.preview_parameter_provider.YugiohCardDataPreviewParameterProvider
import com.example.dueldex.core.ui.util.statusBarPadding
import com.example.dueldex.feature.detail.ui.AddDeckButton
import com.example.dueldex.feature.detail.ui.AttackDefensePowerLayout
import com.example.dueldex.feature.detail.ui.CardDetailScreenSkeleton
import com.example.dueldex.feature.detail.ui.CardPriceLayout
import com.example.dueldex.feature.detail.ui.OnPriceRowClickListener
import com.example.dueldex.core.designsystem.R as DesignR

@Composable
internal fun CardDetailRoute(
    viewModel: CardDetailViewModel = hiltViewModel(),
) {
    val cardDetailUiState by viewModel.cardDetailUiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    var isShowSaveDeckDialog by remember { mutableStateOf(false) }
    var cardDataToSave by remember { mutableStateOf<YugiohCardData?>(null) }

    CardDetailScreen(
        cardDetailUiState = cardDetailUiState,
        scrollState = scrollState,
        onClickedSavedCard = { yugiohCardData ->
            cardDataToSave = yugiohCardData
            isShowSaveDeckDialog = true
        },
        onClickedRetry = viewModel::retryLoadCardDetailData
    )

    if (isShowSaveDeckDialog) {
        cardDataToSave?.let { yugiohCardData ->
            Dialog(
                onDismissRequest = {
                    cardDataToSave = null
                    isShowSaveDeckDialog = false
                }
            ) {
                SavedCardToDeckScreen(
                    yugiohCardData = yugiohCardData
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun CardDetailScreen(
    cardDetailUiState: CardDetailUiState, scrollState: ScrollState,
    onClickedSavedCard: (YugiohCardData) -> Unit,
    onClickedRetry: () -> Unit,
) {
    val context = LocalContext.current
    var webUrlString by remember { mutableStateOf("") }

    LaunchedEffect(webUrlString) {
        if (webUrlString.isNotEmpty()) {
            val customTabsIntent = CustomTabsIntent.Builder().build()
            customTabsIntent.launchUrl(context, Uri.parse(webUrlString))
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarPadding()
    ) {
        when (cardDetailUiState) {
            CardDetailUiState.Error -> {
                UiStateFailedScreen(
                    onClickedRetry = onClickedRetry
                )
            }

            CardDetailUiState.Loading -> {
                CardDetailScreenSkeleton()
            }

            is CardDetailUiState.Success -> {
                val yugiohCardData = cardDetailUiState.yugiohCardDat
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    CarouselPager(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalPaddingWeight = 0.1f,
                        imageList = yugiohCardData.cardImages.map { it.imageUrl },
                    )

                    Column(
                        modifier = Modifier
                            .padding(horizontal = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        AddDeckButton(
                            onClickedSavedCard = { onClickedSavedCard(yugiohCardData) }
                        )

                        yugiohCardData.level?.let {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                repeat(it) {
                                    Image(
                                        modifier = Modifier.size(26.dp),
                                        painter = painterResource(id = DesignR.drawable.level_star),
                                        contentDescription = "Level Icon",
                                    )
                                }
                            }
                        }

                        Text(
                            text = yugiohCardData.name,
                            style = MaterialTheme.ddTypography.fontTitleL,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        AttackDefensePowerLayout(yugiohCardData.atk, yugiohCardData.def)

                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            yugiohCardData.attribute?.let {
                                AttributeTag(
                                    modifier = Modifier,
                                    attribute = it,
                                    attributeSize = AttributeSize.L,
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
                            style = MaterialTheme.ddTypography.fontBodyL,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        CardPriceLayout(
                            modifier = Modifier
                                .fillMaxWidth(),
                            cardPrice = yugiohCardData.cardPrices[0],
                            priceRowClickListener = object : OnPriceRowClickListener {
                                override fun onCardMarketClick() {
                                    webUrlString =
                                        "https://www.cardmarket.com/en/YuGiOh/Products/Search?searchString=${yugiohCardData.name}"
                                }

                                override fun onTcgPlayerClick() {
                                    webUrlString =
                                        "https://www.tcgplayer.com/search/yugioh/product?productLineName=yugioh&q=${yugiohCardData.name}"
                                }

                                override fun onEbayClick() {
                                    webUrlString =
                                        "https://www.ebay.com/sch/i.html?_nkw=${yugiohCardData.name}"
                                }

                                override fun onAmazonClick() {
                                    webUrlString =
                                        "https://www.amazon.com/s?k=${yugiohCardData.name}"
                                }

                                override fun onCoolStuffIncClick() {
                                    webUrlString =
                                        "https://www.coolstuffinc.com/main_search.php?pa=searchOnName&page=1&resultsPerPage=25&q=${yugiohCardData.name}"
                                }
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
    DuelDexTheme {
        DdBackGround {
            CardDetailScreen(
                cardDetailUiState = CardDetailUiState.Success(yugiohCardData),
                scrollState = scrollState,
                onClickedSavedCard = {},
                onClickedRetry = {}
            )
        }
    }
}