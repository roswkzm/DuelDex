package com.example.dueldex.core.datastore

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class UserPreferencesSerializerTest {

    private val userPreferencesSerializer = UserPreferencesSerializer()

    @Test
    fun defaultUserPreferences_isEmpty() {
        assertEquals(
            userPreferences {

            },
            userPreferencesSerializer.defaultValue
        )
    }

    @Test
    fun writingAndReadingUserPreferences_outputsCorrectValue() = runTest {
        val expectedUserPreferences = UserPreferences.newBuilder()
            .addRecentSearches("Search1")
            .addRecentSearches("Search2")
            .setThemeConfig(ThemeConfigProto.THEME_CONFIG_LIGHT)
            .setLocalizationConfig(LocalizationConfigProto.LOCALIZATION_CONFIG_ENGLISH)
            .build()

        val outputStream = ByteArrayOutputStream()
        userPreferencesSerializer.writeTo(expectedUserPreferences, outputStream)

        val inputStream = ByteArrayInputStream(outputStream.toByteArray())
        val actualUserPreferences = userPreferencesSerializer.readFrom(inputStream)

        assertEquals(expectedUserPreferences, actualUserPreferences)
    }
}