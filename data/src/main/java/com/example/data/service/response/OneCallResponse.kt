package com.example.data.service.response

import com.google.gson.annotations.SerializedName

data class OneCallResponse(
    val lat: Double,
    val lon: Double,
    val daily: List<OneCallDailyResponse>
)

data class OneCallDailyResponse(
    val dt: Long,
    val temp: OneCallTempResponse,
    val weather: List<OneCallWeatherResponse>,
    @SerializedName(value = "feels_like")
    val feelsLike: OneCallFeelsLikeResponse,
    val humidity: Int,
    @SerializedName(value = "wind_speed")
    val windSpeed: Double
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
