package com.example.data.service.response

data class OneCallResponse(
    val lat: Double,
    val lon: Double,
    val daily: List<OneCallDailyResponse>
)

data class OneCallDailyResponse(
    val dt: Long,
    val temp: OneCallTempResponse,
    val weather: List<OneCallWeatherResponse>,
    val feels_like: OneCallFeelsLikeResponse,
    val humidity: Int,
    val wind_speed: Double
)

data class OneCallWeatherResponse(
    val description: String,
    val icon: String,
)

data class OneCallFeelsLikeResponse(
    val day: Double,
)

data class OneCallTempResponse(
    val day: Double,
    val max: Double,
    val min: Double,
)
