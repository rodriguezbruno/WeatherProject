package com.example.weatherproject

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.domain.entities.WeatherReport
import com.example.domain.usecase.GetWeatherReportByLocationUseCase
import com.example.domain.utils.Result
import com.example.weatherproject.utils.Status
import com.example.weatherproject.viewmodels.WeatherViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class WeatherViewModelTest {

    companion object {
        private const val LAT = -34.9
        private const val LONG = -54.95
    }

    class TestObserver<T> : Observer<T> {
        val observedValues = mutableListOf<T?>()
        override fun onChanged(value: T?) {
            observedValues.add(value)
        }
    }

    private fun <T> LiveData<T>.testObserver() = TestObserver<T>().also {
        observeForever(it)
    }

    @ObsoleteCoroutinesApi
    private var mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var subject: WeatherViewModel

    @Mock
    lateinit var weatherReportValidResult: Result.Success<WeatherReport>

    @Mock
    lateinit var weatherReportInvalidResult: Result.Failure

    @Mock
    lateinit var weatherReport: WeatherReport

    @Mock
    lateinit var exception: Exception

    @Mock
    lateinit var getWeatherReportByLocation: GetWeatherReportByLocationUseCase


    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        MockitoAnnotations.openMocks(this)
        subject = WeatherViewModel(getWeatherReportByLocation)
    }

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @After
    fun after() {
        mainThreadSurrogate.close()
        Dispatchers.resetMain()
    }

    @Test
    fun getWeatherReportSuccess() {
        val liveDataUnderTest = subject.mainState.testObserver()
        whenever(getWeatherReportByLocation.invoke(LAT, LONG, true)).thenReturn(
            weatherReportValidResult
        )
        whenever(weatherReportValidResult.data).thenReturn(weatherReport)
        runBlocking {
            subject.getWeatherReport(LAT, LONG).join()
        }
        liveDataUnderTest.observedValues.run {
            assertEquals(Status.LOADING, first()?.peekContent()?.responseType)
            assertNotNull(last()?.peekContent())
            last()?.peekContent()?.run {
                assertEquals(Status.SUCCESSFUL, responseType)
                assertEquals(weatherReport, data)
            }
        }
    }

    @Test
    fun getWeatherReportFail() {
        val liveDataUnderTest = subject.mainState.testObserver()
        whenever(getWeatherReportByLocation.invoke(LAT, LONG, true)).thenReturn(
            weatherReportInvalidResult
        )
        whenever(weatherReportInvalidResult.exception).thenReturn(exception)

        runBlocking {
            subject.getWeatherReport(LAT, LONG).join()
        }
        liveDataUnderTest.observedValues.run {
            assertEquals(Status.LOADING, first()?.peekContent()?.responseType)
            assertNotNull(last()?.peekContent())
            last()?.peekContent()?.run {
                assertEquals(Status.ERROR, responseType)
                assertEquals(exception, error)
            }
        }
    }
}