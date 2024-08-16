package com.example.loldex.feature.detail

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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.loldex.core.designsystem.component.LdBackGround
import com.example.loldex.core.designsystem.component.SimpleTag
import com.example.loldex.core.designsystem.theme.LolDexTheme
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.designsystem.theme.ldTypography
import com.example.loldex.core.model.YugiohCardData
import com.example.loldex.core.ui.CarouselPager
import com.example.loldex.core.ui.YugiohCardDataPreviewParameterProvider
import com.example.loldex.core.ui.attribute.AttributeTag
import com.example.loldex.core.ui.util.statusBarPadding
import com.example.loldex.feature.detail.ui.AttackDefensePowerLayout
import com.example.loldex.feature.detail.ui.CardPriceLayout
import com.example.loldex.feature.detail.ui.OnPriceRowClickListener
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
                        Spacer(modifier = Modifier.height(10.dp))
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
    LolDexTheme {
        LdBackGround {
            CardDetailScreen(
                cardDetailUiState = CardDetailUiState.Success(yugiohCardData),
                scrollState = scrollState
            )
        }
    }
}