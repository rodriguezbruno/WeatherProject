package com.example.data.repositories

import com.example.data.service.WeatherService
import com.example.domain.entities.WeatherReport
import com.example.domain.repositories.WeatherReportRepository
import com.example.domain.utils.Result

class WeatherReportRepositoryImpl(private val weatherService: WeatherService) :
    WeatherReportRepository {
    override fun getWeatherReportByLocation(
        lat: Double,
        long: Double,
        getFromRemote: Boolean
    ): Result<WeatherReport> = weatherService.getWeatherReportByLocation(lat, long)
}