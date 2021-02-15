package com.example.weather.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weather.model.WeatherTableModel

@Database(entities = [WeatherTableModel::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDAO() : DAOweather

    companion object {

        @Volatile
        private var INSTANCE: WeatherDatabase? = null

        fun getDatasetClient(context: Context) : WeatherDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, WeatherDatabase::class.java, "WEATHER_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}