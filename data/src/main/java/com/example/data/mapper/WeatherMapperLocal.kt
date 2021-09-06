package com.example.data.mapper

import com.example.data.database.entity.WeatherRoom
import com.example.domain.entities.Weather
import com.example.domain.entities.WeatherReport

class WeatherMapperLocal : BaseMapperRepository<List<WeatherRoom>, WeatherReport> {

    override fun transform(type: List<WeatherRoom>): WeatherReport {

        return WeatherReport(
            type.map { daily ->
                Weather(
                    date = daily.date,
                    temperature = daily.temperature,
                    description = daily.description,
                    tempMax = daily.tempMax,
                    tempMin = daily.tempMin,
                    image = daily.image,
                    thermalSensation = daily.thermalSensation,
                    humidity = daily.humidity,
                    windSpeed = daily.windSpeed
                )
            })
    }

    override fun transformToRepository(type: WeatherReport): List<WeatherRoom> {

        return type.weatherList.map {
            WeatherRoom(
                date = it.date,
                temperature = it.temperature,
                description = it.description,
                tempMax = it.tempMax,
                tempMin = it.tempMin,
                image = it.image,
                thermalSensation = it.thermalSensation,
                humidity = it.humidity,
                windSpeed = it.windSpeed
            )
        }
    }
}
