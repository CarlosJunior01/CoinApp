package com.carlosmagno.exchlist.data.model

import com.google.gson.annotations.SerializedName

data class ExchangeDto(
    @SerializedName("exchange_id")
    val exchangeId: String,
    @SerializedName("name")
    val name: String?,
    @SerializedName("volume_1hrs_usd")
    val volume1HrsUsd: Double?,
    @SerializedName("volume_1day_usd")
    val volume1DayUsd: Double?,
    @SerializedName("volume_1mth_usd")
    val volume1MonthUsd: Double?,
    @SerializedName("data_symbols_count")
    val symbolsCount: Int?,
    @SerializedName("data_quote_start")
    val quoteStart: String?,
    @SerializedName("data_quote_end")
    val quoteEnd: String?,
    @SerializedName("data_orderbook_start")
    val orderbookStart: String?,
    @SerializedName("data_orderbook_end")
    val orderbookEnd: String?,
    @SerializedName("data_trade_start")
    val tradeStart: String?,
    @SerializedName("data_trade_end")
    val tradeEnd: String?,
    @SerializedName("website")
    val website: String?,
    @SerializedName("rank")
    val rank: Int?,
    @SerializedName("integration_status")
    val integrationStatus: String?
)


