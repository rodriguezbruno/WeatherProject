package com.example.weatherproject.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.repositories.WeatherReportRepositoryImpl
import com.example.data.service.WeatherService
import com.example.domain.usecase.GetWeatherReportByLocationUseCase

class AppViewModelFactory(private val context: Context) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == WeatherViewModel::class.java) {
            WeatherViewModel(GetWeatherReportByLocationUseCase().apply {
                weatherReportRepository = WeatherReportRepositoryImpl(
                    WeatherService(context),
                )
            }) as T
        } else {
            super.create(modelClass)
        }
    }
}