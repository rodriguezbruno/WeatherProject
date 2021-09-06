package com.example.domain.entities

class Weather(
    val date: Long,
    val temperature: Double,
    val description: String,
    val tempMax: Double,
    val tempMin: Double,
    val image: String,
    val thermalSensation: Double,
    val humidity: Int,
    val windSpeed: Double
)
