package com.example.weatherproject.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.WeatherReport
import com.example.domain.usecase.GetWeatherReportByLocationUseCase
import com.example.domain.utils.Result
import com.example.weatherproject.utils.Data
import com.example.weatherproject.utils.Event
import com.example.weatherproject.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherViewModel(val getWeatherReportByLocation: GetWeatherReportByLocationUseCase) :
    ViewModel() {

    private var mutableMainState: MutableLiveData<Event<Data<WeatherReport>>> = MutableLiveData()
    val mainState: LiveData<Event<Data<WeatherReport>>>
        get() {
            return mutableMainState
        }

    fun getWeatherReport(lat: Double, long: Double) = viewModelScope.launch {
        mutableMainState.value = Event(Data(responseType = Status.LOADING))
        when (val result =
            withContext(Dispatchers.IO) {
                getWeatherReportByLocation(lat, long, true)
            }) {
            is Result.Failure -> {
                mutableMainState.value =
                    Event(Data(responseType = Status.ERROR, error = result.exception))
            }
            is Result.Success -> {
                mutableMainState.value =
                    Event(Data(responseType = Status.SUCCESSFUL, data = result.data))
            }
        }
    }
}