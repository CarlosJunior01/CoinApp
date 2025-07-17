package com.carlosmagno.exchlist.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosmagno.exchlist.domain.model.Exchange
import com.carlosmagno.exchlist.domain.usecase.GetExchangesUseCase
import kotlinx.coroutines.launch

class ExchangeViewModel(private val getExchangesUseCase: GetExchangesUseCase): ViewModel() {
    var state by mutableStateOf<List<Exchange>>(emptyList())
        private set
    var error by mutableStateOf<String?>(null)
        private set

    fun loadExchanges() {
        viewModelScope.launch {
            try {
                state = getExchangesUseCase()
            } catch (e: Exception) {
                error = e.localizedMessage ?: GENERIC_ERROR
            }
        }
    }

    companion object {
        private const val GENERIC_ERROR = "Erro desconhecido: tente novamente!"
    }
}