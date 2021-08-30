package com.example.data.mapper

import com.example.data.service.response.OneCallResponse
import com.example.domain.entities.*

open class WeatherMapperService : BaseMapperRepository<OneCallResponse, WeatherReport> {

    override fun transform(type: OneCallResponse): WeatherReport {

        return WeatherReport(
            type.daily.map { daily ->
                val dailyWeather = daily.weather.first()
                Weather(
                    date = daily.dt,
                    temperature = daily.temp.day,
                    description = dailyWeather.description,
                    tempMax = daily.temp.max,
                    tempMin = daily.temp.min,
                    image = dailyWeather.icon,
                    thermalSensation = daily.feels_like.day,
                    humidity = daily.humidity,
                    windSpeed = daily.wind_speed
                )
            })
    }
}