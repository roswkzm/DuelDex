package com.example.dueldex.core.network

import JvmUnitTestDemoAssetManager
import com.example.dueldex.core.network.demo.DemoYugiohNetworkDataSource
import com.example.dueldex.core.network.model.response.CardDataResponse
import com.example.dueldex.core.network.model.response.CardImageResponse
import com.example.dueldex.core.network.model.response.CardPriceResponse
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class DemoYugiohNetworkDataSourceTest {

    private lateinit var subject: DemoYugiohNetworkDataSource

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        subject = DemoYugiohNetworkDataSource(
            ioDispatcher = testDispatcher,
            networkJson = Json { ignoreUnknownKeys = true },
            assets = JvmUnitTestDemoAssetManager,
        )
    }

    @Test
    fun testDeserializationOfYugiohPagingList() = runTest(testDispatcher) {
        val response = (subject.getYugiohPagingList(1, 1) as ApiResponse.Success).data
        assertEquals(EXPECTED_CARD_RESPONSE, response.data.first())
    }

    @Test
    fun testDeserializationOfYugiohCardDataById() = runTest(testDispatcher) {
        val response = (subject.getYugiohCardDataById(46986414) as ApiResponse.Success)
            .data.data.filter { it.id == 46986414L }
        assertEquals(EXPECTED_CARD_RESPONSE, response.first())
    }

    @Test
    fun testDeserializationOfYugiohCardDataByName() = runTest(testDispatcher) {
        val response = (subject.getYugiohCardDataByName("Dark Magician") as ApiResponse.Success)
            .data.data.filter { it.name == "Dark Magician" }
        assertEquals(EXPECTED_CARD_RESPONSE, response.first())
    }

    @Test
    fun testDeserializationOfYugiohCardDataBySearchString() = runTest(testDispatcher) {
        val response = (subject.getYugiohCardDataBySearchString(
            searchString = "Dark Magician",
            type = "Normal Monster",
            attribute = "DARK",
            race = "Spellcaster",
            effect = null,
            level = 7
        ) as ApiResponse.Success).data

        val filteredResponse = response.data.filter {
            it.name == "Dark Magician" &&
                    it.type == "Normal Monster" &&
                    it.attribute == "DARK" &&
                    it.race == "Spellcaster" &&
                    it.level == 7
        }

        assertEquals(EXPECTED_CARD_RESPONSE, filteredResponse.first())
    }

    companion object {
        private val EXPECTED_CARD_RESPONSE = CardDataResponse(
            id = 46986414,
            name = "Dark Magician",
            type = "Normal Monster",
            frameType = "normal",
            desc = "''The ultimate wizard in terms of attack and defense.''",
            atk = 2500,
            def = 2100,
            level = 7,
            race = "Spellcaster",
            attribute = "DARK",
            archetype = "Dark Magician",
            ygoprodeckUrl = "https://ygoprodeck.com/card/dark-magician-4003",
            cardImages = listOf(
                CardImageResponse(
                    id = 46986414,
                    imageUrl = "https://images.ygoprodeck.com/images/cards/46986414.jpg",
                    imageUrlSmall = "https://images.ygoprodeck.com/images/cards_small/46986414.jpg",
                    imageUrlCropped = "https://images.ygoprodeck.com/images/cards_cropped/46986414.jpg"
                ),
                CardImageResponse(
                    id = 46986415,
                    imageUrl = "https://images.ygoprodeck.com/images/cards/46986415.jpg",
                    imageUrlSmall = "https://images.ygoprodeck.com/images/cards_small/46986415.jpg",
                    imageUrlCropped = "https://images.ygoprodeck.com/images/cards_cropped/46986415.jpg"
                ),
                CardImageResponse(
                    id = 46986416,
                    imageUrl = "https://images.ygoprodeck.com/images/cards/46986416.jpg",
                    imageUrlSmall = "https://images.ygoprodeck.com/images/cards_small/46986416.jpg",
                    imageUrlCropped = "https://images.ygoprodeck.com/images/cards_cropped/46986416.jpg"
                ),
                CardImageResponse(
                    id = 46986417,
                    imageUrl = "https://images.ygoprodeck.com/images/cards/46986417.jpg",
                    imageUrlSmall = "https://images.ygoprodeck.com/images/cards_small/46986417.jpg",
                    imageUrlCropped = "https://images.ygoprodeck.com/images/cards_cropped/46986417.jpg"
                ),
                CardImageResponse(
                    id = 46986418,
                    imageUrl = "https://images.ygoprodeck.com/images/cards/46986418.jpg",
                    imageUrlSmall = "https://images.ygoprodeck.com/images/cards_small/46986418.jpg",
                    imageUrlCropped = "https://images.ygoprodeck.com/images/cards_cropped/46986418.jpg"
                ),
                CardImageResponse(
                    id = 46986419,
                    imageUrl = "https://images.ygoprodeck.com/images/cards/46986419.jpg",
                    imageUrlSmall = "https://images.ygoprodeck.com/images/cards_small/46986419.jpg",
                    imageUrlCropped = "https://images.ygoprodeck.com/images/cards_cropped/46986419.jpg"
                ),
                CardImageResponse(
                    id = 36996508,
                    imageUrl = "https://images.ygoprodeck.com/images/cards/36996508.jpg",
                    imageUrlSmall = "https://images.ygoprodeck.com/images/cards_small/36996508.jpg",
                    imageUrlCropped = "https://images.ygoprodeck.com/images/cards_cropped/36996508.jpg"
                ),
                CardImageResponse(
                    id = 46986420,
                    imageUrl = "https://images.ygoprodeck.com/images/cards/46986420.jpg",
                    imageUrlSmall = "https://images.ygoprodeck.com/images/cards_small/46986420.jpg",
                    imageUrlCropped = "https://images.ygoprodeck.com/images/cards_cropped/46986420.jpg"
                ),
                CardImageResponse(
                    id = 46986421,
                    imageUrl = "https://images.ygoprodeck.com/images/cards/46986421.jpg",
                    imageUrlSmall = "https://images.ygoprodeck.com/images/cards_small/46986421.jpg",
                    imageUrlCropped = "https://images.ygoprodeck.com/images/cards_cropped/46986421.jpg"
                )
            ),
            cardPrices = listOf(
                CardPriceResponse(
                    cardmarketPrice = "0.02",
                    tcgplayerPrice = "0.30",
                    ebayPrice = "0.99",
                    amazonPrice = "14.45",
                    coolstuffincPrice = "0.39"
                )
            )
        )
    }
}