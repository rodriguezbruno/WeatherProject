package com.example.data.service.api

import com.example.data.service.response.OneCallResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherReportAPI {

    companion object {
        private const val PART: String = "rain,minutely,alerts,hourly,current"
        private const val UNITS: String = "units"
    }

    @GET("onecall")
    fun getWeatherByLocation(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("part") part: String = PART,
        @Query("units") units: String = UNITS
    ): Call<OneCallResponse>
}
