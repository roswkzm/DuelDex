package com.example.dueldex.core.database.model

import com.example.dueldex.core.model.YugiohCardData
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class YugiohCardResourceTest {
    @Test
    fun addition_isCorrect() {
        val yugiohCardEntity = YugiohCardEntity(
            id = 1,
            name = "",
            type = "",
            frameType = "",
            desc = "",
            atk = 1,
            def = 1,
            level = 1,
            race = "",
            attribute = "",
            archetype = "",
            ygoprodeckUrl = "",
            cardImages = listOf(),
            cardPrices = listOf(),
        )
        val yugiohCardData = yugiohCardEntity.asExternalModel()
        assertEquals(
            YugiohCardData(
                id = 1,
                name = "",
                type = "",
                frameType = "",
                desc = "",
                atk = 1,
                def = 1,
                level = 1,
                race = "",
                attribute = "",
                archetype = "",
                ygoprodeckUrl = "",
                cardImages = listOf(),
                cardPrices = listOf(),
            ),
            yugiohCardData
        )
    }
}