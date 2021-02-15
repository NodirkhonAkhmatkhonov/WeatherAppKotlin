package com.example.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.WeatherRepository
import com.example.weather.model.WeatherEntity
import com.example.weather.network.UIState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel() {
    private var repository: WeatherRepository = WeatherRepository()

    private val _liveDataWeather = MutableLiveData<UIState<WeatherEntity>>()
    val liveDataWeather = _liveDataWeather

    fun getWeatherData(cityName: String) {
        viewModelScope.launch {
            repository.getWeatherInfo(cityName).collect { weatherState ->
                _liveDataWeather.value = weatherState
            }
        }
    }
}