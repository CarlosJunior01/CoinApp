package com.carlosmagno.exchlist.di

import androidx.room.Room
import com.carlosmagno.exchlist.BuildConfig
import com.carlosmagno.exchlist.data.local.database.AppDatabase
import com.carlosmagno.exchlist.data.remote.RetrofitClient
import com.carlosmagno.exchlist.data.repository.ExchangeRepositoryImpl
import com.carlosmagno.exchlist.data.repository.FavoriteRepositoryImpl
import com.carlosmagno.exchlist.di.DatabaseConstants.DB_NAME
import com.carlosmagno.exchlist.domain.repository.ExchangeRepository
import com.carlosmagno.exchlist.domain.repository.FavoriteRepository
import com.carlosmagno.exchlist.domain.usecase.GetExchangesUseCase
import com.carlosmagno.exchlist.presentation.viewmodel.ExchangeViewModel
import com.carlosmagno.exchlist.presentation.viewmodel.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    val apiKey = BuildConfig.COIN_API_KEY
    single { RetrofitClient.create(apiKey) }

    single<ExchangeRepository> { ExchangeRepositoryImpl(get()) }

    single<FavoriteRepository> { FavoriteRepositoryImpl(get()) }

    factory { GetExchangesUseCase(get()) }

    viewModel { ExchangeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }

    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            DB_NAME
        ).build()
    }
    single { get<AppDatabase>().favoriteExchangeDao() }
}

object DatabaseConstants {
    const val DB_NAME = "exchange-db"
}