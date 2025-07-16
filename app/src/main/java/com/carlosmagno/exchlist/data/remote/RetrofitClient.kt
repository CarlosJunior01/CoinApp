package com.carlosmagno.exchlist.data.remote

import com.carlosmagno.exchlist.data.remote.RetrofitClient.ApiConstants.BASE_URL
import com.carlosmagno.exchlist.data.remote.RetrofitClient.ApiConstants.HEADER_API_KEY
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun create(apiKey: String): CoinApiService {
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader(HEADER_API_KEY, apiKey)
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(CoinApiService::class.java)
    }

    object ApiConstants {
        const val BASE_URL = "https://rest.coinapi.io/"
        const val HEADER_API_KEY = "X-CoinAPI-Key"
    }
}
