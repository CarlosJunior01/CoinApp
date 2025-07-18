package com.carlosmagno.exchlist.data.repository.factory

import com.carlosmagno.exchlist.data.local.entity.FavoriteExchangeEntity
import com.carlosmagno.exchlist.domain.model.Exchange

object FakeDataFactory {
    fun fakeExchange() = Exchange(
        id = "binance",
        name = "Binance",
        dailyVolumeUsd = 123456.78,
        volume1HrsUsd = 1000.0,
        volume1MonthUsd = 500000.0,
        symbolsCount = 1500,
        quoteStart = "2020-01-01",
        quoteEnd = "2025-01-01",
        orderbookStart = "2020-01-01",
        orderbookEnd = "2025-01-01",
        tradeStart = "2020-01-01",
        tradeEnd = "2025-01-01",
        website = "https://binance.com",
        rank = 1,
        integrationStatus = "active"
    )

    fun fakeEntityList(size: Int) = List(size) {
        FavoriteExchangeEntity(
            id = "exchange_$it",
            name = "Exchange $it",
            dailyVolumeUsd = 10000.0 * it,
            volume1HrsUsd = 100.0 * it,
            volume1MonthUsd = 100000.0 * it,
            symbolsCount = 100 * it,
            quoteStart = "2020-01-01",
            quoteEnd = "2025-01-01",
            orderbookStart = "2020-01-01",
            orderbookEnd = "2025-01-01",
            tradeStart = "2020-01-01",
            tradeEnd = "2025-01-01",
            website = "https://exchange$it.com",
            rank = it,
            integrationStatus = "active"
        )
    }
}
