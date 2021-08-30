package com.example.domain.usecase

import com.example.domain.repositories.WeatherReportRepository

class GetWeatherReportByLocationUseCase {
    lateinit var weatherReportRepository: WeatherReportRepository
    operator fun invoke(lat: Double, long: Double, getFromRemote: Boolean) =
        weatherReportRepository.getWeatherReportByLocation(lat, long, getFromRemote)
}