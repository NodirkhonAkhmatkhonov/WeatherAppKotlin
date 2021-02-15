package com.example.weather.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather.model.WeatherEntity
import com.example.weather.model.WeatherTableModel

@Dao
interface DAOweather {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(cityItem: WeatherTableModel)

    @Query("SELECT * FROM weather")
    fun getWeatherData() : List<WeatherTableModel>

}