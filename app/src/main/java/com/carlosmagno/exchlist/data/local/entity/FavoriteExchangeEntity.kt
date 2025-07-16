package com.carlosmagno.exchlist.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_exchanges")
data class FavoriteExchangeEntity(
    @PrimaryKey val id: String,
    val name: String,
    val dailyVolumeUsd: Double,
    val volume1HrsUsd: Double?,
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

