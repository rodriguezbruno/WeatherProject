package com.example.data.service.response

class WeatherReportBaseResponse<T>(
    var code: Int,
    var status: String,
    var data: T?
)