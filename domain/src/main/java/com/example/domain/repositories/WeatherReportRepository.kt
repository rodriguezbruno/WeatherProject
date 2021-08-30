package com.example.domain.repositories

import com.example.domain.entities.WeatherReport
import com.example.domain.utils.Result

interface WeatherReportRepository {
    fun getWeatherReportByLocation(
        lat: Double,
        long: Double,
        getFromRemote: Boolean
    ): Result<WeatherReport>
}