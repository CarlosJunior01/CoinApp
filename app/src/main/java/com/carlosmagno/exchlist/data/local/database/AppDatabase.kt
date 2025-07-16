package com.carlosmagno.exchlist.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carlosmagno.exchlist.data.local.dao.FavoriteExchangeDao
import com.carlosmagno.exchlist.data.local.entity.FavoriteExchangeEntity

@Database(entities = [FavoriteExchangeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteExchangeDao(): FavoriteExchangeDao
}