package com.example.domain.usecase

import com.example.domain.repositories.WeatherReportRepository

class GetWeatherReportByLocationUseCase {
    lateinit var weatherReportRepository: WeatherReportRepository
    operator fun invoke(lat: Double = 0.0, long: Double = 0.0, getFromRemote: Boolean) =
        weatherReportRepository.getWeatherReportByLocation(lat, long, getFromRemote)
}