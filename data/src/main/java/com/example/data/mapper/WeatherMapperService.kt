package com.example.data.mapper

import com.example.data.service.response.OneCallResponse
import com.example.domain.entities.Weather
import com.example.domain.entities.WeatherReport

open class WeatherMapperService : BaseMapperRepository<OneCallResponse, WeatherReport> {

    companion object {
        private const val URL_ICON = "https://openweathermap.org/img/wn/%s.png"
    }

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
                    image = String.format(URL_ICON, dailyWeather.icon),
                    thermalSensation = daily.feelsLike.day,
                    humidity = daily.humidity,
                    windSpeed = daily.windSpeed
                )
            })
    }
}