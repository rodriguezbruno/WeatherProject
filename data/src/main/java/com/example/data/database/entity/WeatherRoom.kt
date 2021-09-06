package com.example.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherRoom (
    @PrimaryKey
    var date: Long,
    var temperature: Double,
    var description: String,
    @ColumnInfo(name = "temp_max")
    var tempMax: Double,
    @ColumnInfo(name = "temp_min")
    var tempMin: Double,
    var image: String,
    @ColumnInfo(name = "thermal_sensation")
    var thermalSensation: Double,
    var humidity: Int,
    @ColumnInfo(name = "wind_speed")
    var windSpeed: Double
)