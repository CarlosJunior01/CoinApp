package com.carlosmagno.exchlist.data.repository.factory

import com.carlosmagno.exchlist.data.model.ExchangeDto

object ExchangeFactory {
    private fun createExchangeDto(
        exchangeId: String = "",
        name: String = "Binance",
        volume1HrsUsd: Double = 1.000,
        volume1DayUsd: Double = 1.000,
        volume1MonthUsd: Double = 1.000,
        symbolsCount: Int = 1,
        quoteStart: String? = "",
        quoteEnd: String = "",
        orderbookStart: String = "",
        orderbookEnd: String = "",
        tradeStart: String = "",
        tradeEnd: String = "",
        rank: Int = 1,
        integrationStatus: String = "",
        website: String = "https://www.binance.com",
    ): ExchangeDto {
        return ExchangeDto(
            exchangeId = exchangeId ,
            name = name,
            volume1HrsUsd = volume1HrsUsd,
            volume1MonthUsd = volume1MonthUsd,
            symbolsCount = symbolsCount,
            quoteStart = quoteStart,
            quoteEnd = quoteEnd,
            orderbookStart = orderbookStart,
            orderbookEnd = orderbookEnd,
            tradeStart = tradeStart,
            tradeEnd = tradeEnd,
            website = website,
            rank = rank,
            volume1DayUsd = volume1DayUsd,
            integrationStatus = integrationStatus
        )
    }

    fun fakeExchangeDtoList(size: Int = 3): List<ExchangeDto> {
        return (1..size).map {
            createExchangeDto(
                exchangeId = "exchange_$it",
                name = "Exchange $it",
                volume1HrsUsd = it * 10.0,
                volume1DayUsd = it * 100.0,
                volume1MonthUsd = it * 1000.0,
                symbolsCount = it * 5,
                rank = it,
                website = "https://www.exchange$it.com"
            )
        }
    }
}