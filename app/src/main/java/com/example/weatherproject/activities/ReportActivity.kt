package com.example.weatherproject.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entities.WeatherReport
import com.example.weatherproject.R
import com.example.weatherproject.databinding.ReportActivityBinding
import com.example.weatherproject.utils.Data
import com.example.weatherproject.utils.Event
import com.example.weatherproject.utils.ReportAdapter
import com.example.weatherproject.utils.Status
import com.example.weatherproject.viewmodels.AppViewModelProvider
import com.example.weatherproject.viewmodels.WeatherViewModel

class ReportActivity : AppCompatActivity() {

    companion object {
        private const val LAT = -34.9
        private const val LONG = -54.95
    }

    private val viewModel by lazy {
        AppViewModelProvider(this).get(WeatherViewModel::class.java)
    }

    private lateinit var binding: ReportActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ReportActivityBinding.inflate((layoutInflater))
        setContentView(binding.root)

        viewModel.mainState.observe(::getLifecycle, ::updateUI)
        viewModel.getWeatherReport(LAT, LONG)

        binding.rclWeatherReport.layoutManager = LinearLayoutManager(this)
    }

    private fun updateUI(weatherReport: Event<Data<WeatherReport>>) {
        when (weatherReport.peekContent().responseType) {
            Status.ERROR -> {
                hideProgressForError()
                weatherReport.peekContent().error?.message?.let { showMessage(it) }
                binding.textNotFoundError.text = getString(R.string.txt_weather_error)
            }
            Status.LOADING -> {
                showProgress()
            }
            Status.SUCCESSFUL -> {
                hideProgressForSuccess()
                weatherReport.peekContent().data?.let {
                    binding.rclWeatherReport.adapter = ReportAdapter(it.weatherList)
                }
            }
        }
    }

    private fun showProgress() {
        binding.progressBarWeatherReport.visibility = View.VISIBLE
        binding.textNotFoundError.visibility = View.INVISIBLE
    }

    private fun hideProgressForError() {
        binding.progressBarWeatherReport.visibility = View.INVISIBLE
        binding.textNotFoundError.visibility = View.VISIBLE
    }

    private fun hideProgressForSuccess() {
        binding.progressBarWeatherReport.visibility = View.INVISIBLE
        binding.textNotFoundError.visibility = View.INVISIBLE
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}