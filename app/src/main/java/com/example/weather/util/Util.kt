package com.example.weather.util

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Util {
    val retrofit = Retrofit.Builder()
        .client(OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
        )
        .baseUrl("https://api.openweathermap.org")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}