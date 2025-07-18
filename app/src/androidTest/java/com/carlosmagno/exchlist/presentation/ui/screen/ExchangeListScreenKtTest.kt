package com.carlosmagno.exchlist.presentation.ui.screen

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.carlosmagno.exchlist.R
import com.carlosmagno.exchlist.domain.model.Exchange
import com.carlosmagno.exchlist.domain.repository.ExchangeRepository
import com.carlosmagno.exchlist.domain.usecase.GetExchangesUseCase
import com.carlosmagno.exchlist.presentation.viewmodel.ExchangeViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExchangeListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun createViewModelWith(exchanges: List<Exchange>): ExchangeViewModel {
        val dummyRepo = object : ExchangeRepository {
            override suspend fun getExchanges() = exchanges
            override suspend fun getExchangeById(id: String) = exchanges.find { it.id == id }
        }
        return ExchangeViewModel(GetExchangesUseCase(dummyRepo))
    }

    @Test
    fun exchangeList_displaysItemsCorrectly() {
        val initial = listOf(
            Exchange(
                id = "BINANCE",
                name = "Binance",
                volume1HrsUsd = null,
                dailyVolumeUsd = 1000.0,
                volume1MonthUsd = null,
                symbolsCount = null,
                quoteStart = null,
                quoteEnd = null,
                orderbookStart = null,
                orderbookEnd = null,
                tradeStart = null,
                tradeEnd = null,
                website = null,
                rank = null,
                integrationStatus = null
            )
        )
        val vm = createViewModelWith(initial)

        composeTestRule.setContent {
            val navController = rememberNavController()
            ExchangeListScreen(
                navController = navController,
                onExchangeClick = {},
                viewModel = vm
            )
        }

        composeTestRule.onNodeWithText("Binance").assertExists()

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val volumeLabel = context.getString(R.string.volume_24h)
        composeTestRule.onNodeWithText(volumeLabel).assertExists()

        composeTestRule.onNodeWithText("1.00K").assertExists()
    }

    @Test
    fun exchangeList_clickItem_triggersCallback() {
        val initial = listOf(
            Exchange(
                id = "COINBASE",
                name = "Coinbase",
                volume1HrsUsd = null,
                dailyVolumeUsd = 2000.0,
                volume1MonthUsd = null,
                symbolsCount = null,
                quoteStart = null,
                quoteEnd = null,
                orderbookStart = null,
                orderbookEnd = null,
                tradeStart = null,
                tradeEnd = null,
                website = null,
                rank = null,
                integrationStatus = null
            )
        )
        val vm = createViewModelWith(initial)
        val clicked = mutableListOf<Exchange>()

        composeTestRule.setContent {
            val navController = rememberNavController()
            ExchangeListScreen(
                navController = navController,
                onExchangeClick = { clicked.add(it) },
                viewModel = vm
            )
        }

        composeTestRule.onNodeWithText("Coinbase").performClick()

        assert(clicked.first().id == "COINBASE")
    }
}