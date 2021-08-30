package com.example.data.service

import android.content.Context
import com.example.data.WeatherReportRequestGenerator
import com.example.data.mapper.WeatherMapperService
import com.example.data.service.api.WeatherReportAPI
import com.example.domain.entities.WeatherReport
import com.example.domain.utils.Result
import java.io.IOException

class WeatherService(context: Context) {

    private val api: WeatherReportRequestGenerator = WeatherReportRequestGenerator(context)
    private val mapper: WeatherMapperService = WeatherMapperService()

    fun getWeatherReportByLocation(lat: Double, long: Double): Result<WeatherReport> {
        val callResponse =
            api.createService(WeatherReportAPI::class.java).getWeatherByLocation(lat, long)
        try {
            val response = callResponse.execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    mapper.transform(it)
                }?.let {
                    return Result.Success(it)
                }
            }
            return Result.Failure(Exception(response.message()))
        } catch (e: RuntimeException) {
            return Result.Failure(Exception("Bad request/response"))
        } catch (e: IOException) {
            return Result.Failure(Exception("Bad request/response"))
        }
    }
}