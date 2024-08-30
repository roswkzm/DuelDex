package com.example.loldex.feature.detail.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.designsystem.theme.ldTypography
import com.example.loldex.core.model.YugiohCardData
import com.example.loldex.core.model.YugiohCardPrice
import com.example.loldex.core.model.toMarketNameList
import com.example.loldex.core.model.toPriceList
import com.example.loldex.core.ui.ExpandableCard
import com.example.loldex.core.ui.preview_parameter_provider.YugiohCardDataPreviewParameterProvider
import com.example.loldex.feature.detail.R
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottomAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStartAxis
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLine
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.common.fill
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries
import com.patrykandpatrick.vico.core.cartesian.layer.LineCartesianLayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.loldex.core.designsystem.R as DesignR

@Composable
fun CardPriceLayout(
    modifier: Modifier = Modifier,
    cardPrice: YugiohCardPrice,
    priceRowClickListener: OnPriceRowClickListener
) {
    val modelProducer = remember { CartesianChartModelProducer() }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.Default) {
            modelProducer.runTransaction {
                lineSeries {
                    series(
                        cardPrice.toPriceList()
                    )
                }
            }
        }
    }

    ExpandableCard(
        modifier = modifier,
        color = Color.Gray,
        title = "Show Prices",
        titleColor = Color.Black,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PriceByCompanyRow(
                    painterResource = DesignR.drawable.card_market_logo,
                    price = cardPrice.cardMarketPrice,
                    onClickedPriceRow = { priceRowClickListener.onCardMarketClick() }
                )
                PriceByCompanyRow(
                    painterResource = DesignR.drawable.tcg_player_logo,
                    price = cardPrice.tcgPlayerPrice,
                    onClickedPriceRow = { priceRowClickListener.onTcgPlayerClick() }
                )
                PriceByCompanyRow(
                    painterResource = DesignR.drawable.ebay_logo,
                    price = cardPrice.ebayPrice,
                    onClickedPriceRow = { priceRowClickListener.onEbayClick() }
                )
                PriceByCompanyRow(
                    painterResource = DesignR.drawable.amazon_logo,
                    price = cardPrice.amazonPrice,
                    onClickedPriceRow = { priceRowClickListener.onAmazonClick() }
                )
                PriceByCompanyRow(
                    painterResource = DesignR.drawable.cool_stuff_inc_logo,
                    price = cardPrice.coolStuffIncPrice,
                    onClickedPriceRow = { priceRowClickListener.onCoolStuffIncClick() }
                )

                PriceByChart(
                    modelProducer = modelProducer,
                    cardPrice = cardPrice,
                )
            }
        }
    )
}

@Composable
fun PriceByCompanyRow(
    @DrawableRes painterResource: Int,
    price: String,
    onClickedPriceRow: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClickedPriceRow() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .height(40.dp)
                .weight(1f),
            painter = painterResource(id = painterResource),
            contentDescription = "Card Selling Company Logo",
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier,
            text = "$price$",
            style = MaterialTheme.ldTypography.fontTitleXS,
            color = Color.Black,
        )
        Spacer(modifier = Modifier.width(30.dp))
        Icon(
            modifier = Modifier
                .size(25.dp),
            imageVector = Icons.Filled.ArrowForward,
            contentDescription = stringResource(id = R.string.add_deck_btn_text),
            tint = Color.Black
        )
    }
}

@Composable
fun PriceByChart(
    modelProducer: CartesianChartModelProducer,
    cardPrice: YugiohCardPrice,
) {
    val marketNameList = cardPrice.toMarketNameList()
    val priceList = cardPrice.toPriceList()
    val marker = rememberMarker()

    CartesianChartHost(
        chart =
        rememberCartesianChart(
            rememberLineCartesianLayer(
                LineCartesianLayer.LineProvider.series(
                    rememberLine(remember { LineCartesianLayer.LineFill.single(fill(Color(0xffa485e0))) })
                )
            ),
            startAxis = rememberStartAxis(),
            bottomAxis = rememberBottomAxis(
                guideline = null,
                valueFormatter = { x, _, _ -> marketNameList.getOrNull(x.toInt()) ?: x.toString() }
            ),
            marker = marker,
            persistentMarkers = {
                marker at priceList.indexOf(priceList.min())
                marker at priceList.indexOf(priceList.max())
            },
        ),
        modelProducer = modelProducer,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
    )
}

interface OnPriceRowClickListener {
    fun onCardMarketClick()
    fun onTcgPlayerClick()
    fun onEbayClick()
    fun onAmazonClick()
    fun onCoolStuffIncClick()
}

@ThemePreviews
@Composable
fun CardPriceLayoutPreview(
    @PreviewParameter(YugiohCardDataPreviewParameterProvider::class) yugiohCardList: List<YugiohCardData>
) {
    val yugiohCardData = yugiohCardList[0]
    CardPriceLayout(
        modifier = Modifier
            .fillMaxWidth(),
        cardPrice = yugiohCardData.cardPrices[0],
        priceRowClickListener = object : OnPriceRowClickListener {
            override fun onCardMarketClick() {
            }

            override fun onTcgPlayerClick() {
            }

            override fun onEbayClick() {
            }

            override fun onAmazonClick() {
            }

            override fun onCoolStuffIncClick() {
            }
        }
    )
}