package com.carlosmagno.exchlist.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.carlosmagno.exchlist.data.local.entity.FavoriteExchangeEntity

@Dao
interface FavoriteExchangeDao {

    @Query("SELECT * FROM favorite_exchanges")
    suspend fun getAllFavorites(): List<FavoriteExchangeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(exchange: FavoriteExchangeEntity)

    @Delete
    suspend fun deleteFavorite(exchange: FavoriteExchangeEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_exchanges WHERE id = :id)")
    suspend fun isFavorite(id: String): Boolean
}

