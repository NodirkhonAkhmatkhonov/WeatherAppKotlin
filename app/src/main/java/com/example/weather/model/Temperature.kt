package com.example.weather.model

import com.squareup.moshi.Json

data class Temperature(@Json(name = "temp") val temp: String)
