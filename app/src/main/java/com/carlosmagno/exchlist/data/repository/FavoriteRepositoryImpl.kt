package com.carlosmagno.exchlist.data.repository

import com.carlosmagno.exchlist.data.local.dao.FavoriteExchangeDao
import com.carlosmagno.exchlist.data.mapper.toDomain
import com.carlosmagno.exchlist.data.mapper.toEntity
import com.carlosmagno.exchlist.domain.model.Exchange
import com.carlosmagno.exchlist.domain.repository.FavoriteRepository

class FavoriteRepositoryImpl(private val dao: FavoriteExchangeDao) : FavoriteRepository {
    override suspend fun getFavorites(): List<Exchange> =
        dao.getAllFavorites().map { it.toDomain() }

    override suspend fun addFavorite(exchange: Exchange) {
        dao.insertFavorite(exchange.toEntity())
    }

    override suspend fun removeFavorite(exchange: Exchange) {
        dao.deleteFavorite(exchange.toEntity())
    }

    override suspend fun isFavorite(id: String): Boolean {
        return dao.isFavorite(id)
    }
}
