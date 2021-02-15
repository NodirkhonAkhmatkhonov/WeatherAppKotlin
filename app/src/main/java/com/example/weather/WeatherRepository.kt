package com.example.weather

import android.util.Log
import com.example.weather.model.WeatherEntity
import com.example.weather.network.ApiService
import com.example.weather.network.Failure
import com.example.weather.network.Success
import com.example.weather.network.UIState
import com.example.weather.util.Util
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WeatherRepository(private val apiService: ApiService = Util.retrofit.create(ApiService::class.java)) {

    private val TAG = "WeatherRepository"

    suspend fun getWeatherInfo(cityName: String) = flow<UIState<WeatherEntity>> {
        val response = apiService.getWeatherInfo(cityName, "dd3bbc6aac2d098729b2ce1c03af9966")

        if (response.isSuccessful && response.body() != null) {
            emit(Success(response.body()!!))
        } else {
            emit(Failure("Unknown error occurred!!"))
        }
    }.flowOn(Dispatchers.IO)

}