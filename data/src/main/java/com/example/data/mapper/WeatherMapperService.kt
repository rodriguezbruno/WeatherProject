package com.example.data.mapper

import com.example.data.service.response.*
import com.example.data.service.response.OneCallDailyResponse
import com.example.domain.entities.Weather
import com.example.domain.entities.WeatherReport

open class WeatherMapperService : BaseMapperRepository<OneCallResponse, WeatherReport> {

    companion object {
        const val URL_ICON = "https://openweathermap.org/img/wn/%s.png"
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

    override fun transformToRepository(type: WeatherReport): OneCallResponse {

        return OneCallResponse(
            daily = type.weatherList.map {
                val listOneCallWeather: List<OneCallWeatherResponse> = listOf(
                    OneCallWeatherResponse(
                        description = it.description,
                        icon = it.image
                    )
                )
                OneCallDailyResponse(
                    dt = it.date,
                    temp = OneCallTempResponse(
                        day = it.temperature,
                        max = it.tempMax,
                        min = it.tempMin
                    ),
                    weather = listOneCallWeather,
                    feelsLike = OneCallFeelsLikeResponse(
                        day = it.thermalSensation
                    ),
                    humidity = it.humidity,
                    windSpeed = it.windSpeed
                )
            })
    }
}