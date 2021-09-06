package com.example.data.repositories

import com.example.data.database.WeatherDataBase
import com.example.data.mapper.WeatherMapperLocal
import com.example.data.service.WeatherService
import com.example.domain.entities.WeatherReport
import com.example.domain.repositories.WeatherReportRepository
import com.example.domain.utils.Result

class WeatherReportRepositoryImpl(
    private val weatherService: WeatherService,
    private val weatherDatabase: WeatherDataBase
) : WeatherReportRepository {

    private val mapper = WeatherMapperLocal()

    override fun getWeatherReportByLocation(lat: Double, long: Double, getFromRemote: Boolean):
            Result<WeatherReport> =
        if (getFromRemote) {
            val weatherReportResult = weatherService.getWeatherReportByLocation(lat, long)
            if (weatherReportResult is Result.Success) {
                insertOrUpdateWeather(weatherReportResult.data)
            }
            weatherReportResult
        } else {
             weatherDatabase.weatherDao().findAll()?.let {
                return Result.Success(mapper.transform(it))
            } ?: Result.Failure(Exception("Character not found"))
        }

    private fun insertOrUpdateWeather(weather: WeatherReport) {
        weatherDatabase.weatherDao().insertAll(mapper.transformToRepository(weather))
    }
}
