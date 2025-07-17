package com.carlosmagno.exchlist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosmagno.exchlist.domain.model.Exchange
import com.carlosmagno.exchlist.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: FavoriteRepository
) : ViewModel() {

    private val _favorites = MutableStateFlow<List<Exchange>>(emptyList())
    val favorites: StateFlow<List<Exchange>> = _favorites

    init {
        loadFavorites()
    }

    fun loadFavorites() {
        viewModelScope.launch {
            val favorites = repository.getFavorites()
            _favorites.value = favorites
        }
    }

    fun toggleFavorite(exchange: Exchange) {
        viewModelScope.launch {
            val id = exchange.id ?: return@launch

            val isFav = repository.isFavorite(id)

            if (isFav) {
                repository.removeFavorite(exchange)
            } else {
                repository.addFavorite(exchange)
            }

            loadFavorites()
        }
    }

    fun isFavorite(id: String?): Boolean {
        return id != null && _favorites.value.any { it.id == id }
    }
}


