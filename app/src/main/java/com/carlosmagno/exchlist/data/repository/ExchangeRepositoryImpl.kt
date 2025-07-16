package com.carlosmagno.exchlist.data.repository

import com.carlosmagno.exchlist.data.mapper.toDomain
import com.carlosmagno.exchlist.data.remote.CoinApiService
import com.carlosmagno.exchlist.domain.model.Exchange
import com.carlosmagno.exchlist.domain.repository.ExchangeRepository

class ExchangeRepositoryImpl(private val api: CoinApiService) : ExchangeRepository {

     private var cachedExchanges: List<Exchange>? = null

     override suspend fun getExchanges(): List<Exchange> {
          return cachedExchanges ?: run {
               val result = api.getExchanges().map { it.toDomain() }
               cachedExchanges = result
               result
          }
     }

     override suspend fun getExchangeById(id: String): Exchange? {
          val exchanges = cachedExchanges ?: getExchanges()

          return exchanges.find { it.id == id }
     }
}