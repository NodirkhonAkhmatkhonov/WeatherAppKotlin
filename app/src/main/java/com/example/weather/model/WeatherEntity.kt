package com.example.weather.model

import androidx.room.Entity
import com.squareup.moshi.Json

data class WeatherEntity(val name: String,
                         val weather: List<WeatherItem>,
                         val main: Temperature)
