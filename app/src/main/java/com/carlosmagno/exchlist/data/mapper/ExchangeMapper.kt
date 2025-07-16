package com.carlosmagno.exchlist.data.mapper

import com.carlosmagno.exchlist.data.local.entity.FavoriteExchangeEntity
import com.carlosmagno.exchlist.data.model.ExchangeDto
import com.carlosmagno.exchlist.domain.model.Exchange

fun ExchangeDto.toDomain() = Exchange(
    id = exchangeId,
    name = name,
    volume1HrsUsd = volume1HrsUsd,
    dailyVolumeUsd = volume1DayUsd,
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
    integrationStatus = integrationStatus
)

fun Exchange.toEntity() = FavoriteExchangeEntity(
    id = this.id,
    name = this.name ?: "",
    dailyVolumeUsd = this.dailyVolumeUsd ?: 0.0,
    volume1HrsUsd = this.volume1HrsUsd,
    volume1MonthUsd = this.volume1MonthUsd,
    symbolsCount = this.symbolsCount,
    quoteStart = this.quoteStart,
    quoteEnd = this.quoteEnd,
    orderbookStart = this.orderbookStart,
    orderbookEnd = this.orderbookEnd,
    tradeStart = this.tradeStart,
    tradeEnd = this.tradeEnd,
    website = this.website,
    rank = this.rank,
    integrationStatus = this.integrationStatus
)

fun FavoriteExchangeEntity.toDomain(): Exchange {
    return Exchange(
        id = this.id,
        name = this.name,
        dailyVolumeUsd = this.dailyVolumeUsd,
        volume1HrsUsd = this.volume1HrsUsd,
        volume1MonthUsd = this.volume1MonthUsd,
        symbolsCount = this.symbolsCount,
        quoteStart = this.quoteStart,
        quoteEnd = this.quoteEnd,
        orderbookStart = this.orderbookStart,
        orderbookEnd = this.orderbookEnd,
        tradeStart = this.tradeStart,
        tradeEnd = this.tradeEnd,
        website = this.website,
        rank = this.rank,
        integrationStatus = this.integrationStatus
    )
}

