package com.carlosmagno.exchlist.domain.repository

import com.carlosmagno.exchlist.domain.model.Exchange

interface ExchangeRepository {
    suspend fun getExchanges(): List<Exchange>
    suspend fun getExchangeById(id: String): Exchange?
}