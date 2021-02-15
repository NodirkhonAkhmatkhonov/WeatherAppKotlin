package com.example.weather.network

import com.example.weather.model.WeatherEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    // api.openweathermap.org/data/2.5/weather?q=London&appid={API key}
    @GET("/data/2.5/weather")
    suspend fun getWeatherInfo(@Query("q") name: String, @Query("appid") key: String): Response<WeatherEntity>

}