package com.carlosmagno.exchlist.domain.usecase

import com.carlosmagno.exchlist.domain.model.Exchange
import com.carlosmagno.exchlist.domain.repository.ExchangeRepository

class GetExchangesUseCase(private val repository: ExchangeRepository) {
    suspend operator fun invoke(): List<Exchange> = repository.getExchanges()
}