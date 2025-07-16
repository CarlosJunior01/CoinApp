package com.carlosmagno.exchlist.data.remote

import com.carlosmagno.exchlist.data.model.ExchangeDto
import retrofit2.http.GET

interface CoinApiService {
    @GET(ENDPOINT_EXCHANGES)
    suspend fun getExchanges(): List<ExchangeDto>

    companion object {
        private const val ENDPOINT_EXCHANGES = "v1/exchanges"
    }
}