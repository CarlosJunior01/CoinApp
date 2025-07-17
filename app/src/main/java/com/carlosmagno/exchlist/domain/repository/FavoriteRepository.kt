package com.carlosmagno.exchlist.domain.repository

import com.carlosmagno.exchlist.domain.model.Exchange

interface FavoriteRepository {
    suspend fun getFavorites(): List<Exchange>
    suspend fun addFavorite(exchange: Exchange)
    suspend fun removeFavorite(exchange: Exchange)
    suspend fun isFavorite(id: String): Boolean
}
