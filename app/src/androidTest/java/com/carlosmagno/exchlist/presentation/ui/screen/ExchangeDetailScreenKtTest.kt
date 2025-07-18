package com.carlosmagno.exchlist.presentation.ui.screen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.carlosmagno.exchlist.domain.model.Exchange
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExchangeDetailScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val mockExchange = Exchange(
        id = "binance",
        name = "Binance",
        volume1HrsUsd = 150000000.0,
        dailyVolumeUsd = 3200000000.0,
        volume1MonthUsd = 12300000000.0,
        symbolsCount = 250,
        quoteStart = "2017-01-01",
        quoteEnd = "2025-01-01",
        orderbookStart = "2017-01-01",
        orderbookEnd = "2025-01-01",
        tradeStart = "2017-01-01",
        tradeEnd = "2025-01-01",
        website = "www.binance.com",
        rank = 1,
        integrationStatus = "Active"
    )

    @Test
    fun testExchangeDetailScreenDisplaysCorrectData() {
        composeTestRule.setContent {
            ExchangeDetailScreen(
                exchange = mockExchange,
                isFavorite = false,
                onToggleFavorite = {}
            )
        }

        composeTestRule.onRoot().printToLog("TEST_TAG")
        composeTestRule.onAllNodesWithText("Binance").onFirst().assertIsDisplayed()
        composeTestRule.onNodeWithText("ID: binance").assertIsDisplayed()
        composeTestRule.onNodeWithText("Status: Active").assertIsDisplayed()
        composeTestRule.onNodeWithText("Última 1 hora").assertIsDisplayed()
        composeTestRule.onNodeWithText("Últimas 24 horas").assertIsDisplayed()
        composeTestRule.onNodeWithText("Últimos 30 dias").assertIsDisplayed()
        composeTestRule.onNodeWithText("www.binance.com").assertIsDisplayed()
    }

    @Test
    fun exchangeDetail_showsExchangeName() {
        composeTestRule.setContent {
            ExchangeDetailScreen(
                exchange = mockExchange,
                isFavorite = false,
                onToggleFavorite = {}
            )
        }

        composeTestRule
            .onAllNodesWithText("Binance")
            .onFirst()
            .assertIsDisplayed()
    }

    @Test
    fun exchangeDetail_togglesFavoriteStateOnClick() {
        var wasToggled = false

        composeTestRule.setContent {
            ExchangeDetailScreen(
                exchange = mockExchange,
                isFavorite = false,
                onToggleFavorite = {
                    wasToggled = true
                }
            )
        }

        composeTestRule
            .onNodeWithContentDescription("Favoritar")
            .assertIsDisplayed()
            .performClick()

        assert(wasToggled)
    }
}