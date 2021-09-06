package com.example.data.database.response

import com.example.data.service.response.OneCallResponse
import com.google.gson.annotations.SerializedName

class DataBaseResponse<T> (
    @SerializedName("results") val weather: List<OneCallResponse>,
    val offset: Int,
    val limit: Int,
    val total: Int
)