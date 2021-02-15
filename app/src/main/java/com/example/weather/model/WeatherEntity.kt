package com.example.weather.model

import com.squareup.moshi.Json

data class WeatherEntity(val name: String,
                         val weather: List<WeatherItem>,
                         val main: Temperature)
