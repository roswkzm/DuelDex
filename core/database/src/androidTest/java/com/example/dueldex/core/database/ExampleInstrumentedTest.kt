package com.example.dueldex.core.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.dueldex.core.database.dao.DeckDao
import com.example.dueldex.core.database.dao.YugiohCardDao
import com.example.dueldex.core.database.model.DeckEntity
import com.example.dueldex.core.database.model.YugiohCardEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DatabaseTest {

    private lateinit var deckDao: DeckDao
    private lateinit var yugiohCardDao: YugiohCardDao
    private lateinit var db: DdDataBase

    val sampleCard = YugiohCardEntity(
        id = 1,
        name = "Dark Magician",
        type = "Spellcaster",
        frameType = "normal",
        desc = "The ultimate wizard.",
        atk = 2500,
        def = 2100,
        level = 7,
        race = "Spellcaster",
        attribute = "DARK",
        archetype = "Magician",
        ygoprodeckUrl = "http://example.com",
        cardImages = emptyList(),
        cardPrices = emptyList()
    )

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            DdDataBase::class.java,
        ).build()
        deckDao = db.deckDao()
        yugiohCardDao = db.yugiohCardDao()
    }

    @After
    fun closeDb() = db.close()

    @Test
    fun testInsertAndGetDeck() = runTest {
        val deck1 = DeckEntity(deckName = "Test Deck 1")
        val deck2 = DeckEntity(deckName = "Test Deck 2")

        deckDao.insertDeck(deck1)
        deckDao.insertDeck(deck2)
        val decks = deckDao.getAllDecks().first()

        assertEquals(
            listOf("Test Deck 1", "Test Deck 2"),
            decks.map { it.deckName }
        )
    }

    @Test
    fun testInsertAndDeleteDeck() = runTest {
        val deck = DeckEntity(deckName = "Test Deck")
        val deckId = deckDao.insertDeck(deck)

        deckDao.deleteDeck(DeckEntity(id = deckId, deckName = "Test Deck"))
        val decks = deckDao.getAllDecks().first()

        assertTrue(decks.isEmpty())
    }

    @Test
    fun testUpdateDeckName() = runTest {
        val deck = DeckEntity(deckName = "Original Deck Name")
        val deckId = deckDao.insertDeck(deck)

        val updatedDeck = DeckEntity(id = deckId, deckName = "Updated Deck Name")
        deckDao.updateDeck(updatedDeck)

        val decks = deckDao.getAllDecks().first()
        assertEquals("Updated Deck Name", decks[0].deckName)
    }

    @Test
    fun testInsertDeckAndAddCardRelation() = runTest {
        val deck = DeckEntity(deckName = "Test Deck")
        val deckId = deckDao.insertDeck(deck)

        deckDao.insertDeckCard(deckId, sampleCard)

        val deckWithCards = deckDao.getDeckWithCards(deckId).first()

        assertEquals(sampleCard, deckWithCards.cards[0])
    }

    @Test
    fun testInsertDeckAndDeleteCardRelation() = runTest {
        val deck = DeckEntity(deckName = "Test Deck")
        val deckId = deckDao.insertDeck(deck)

        deckDao.insertDeckCard(deckId, sampleCard)
        deckDao.deleteCardFromDeck(deckId, sampleCard.id)

        val deckWithCards = deckDao.getDeckWithCards(deckId).first()

        assertEquals(
            listOf<YugiohCardEntity>(),
            deckWithCards.cards
        )
    }
}