package com.carlosmagno.exchlist.domain.model

data class Exchange(
    val id: String,
    val name: String?,
    val volume1HrsUsd: Double?,
    val dailyVolumeUsd: Double?,
    val volume1MonthUsd: Double?,
    val symbolsCount: Int?,
    val quoteStart: String?,
    val quoteEnd: String?,
    val orderbookStart: String?,
    val orderbookEnd: String?,
    val tradeStart: String?,
    val tradeEnd: String?,
    val website: String?,
    val rank: Int?,
    val integrationStatus: String?
)