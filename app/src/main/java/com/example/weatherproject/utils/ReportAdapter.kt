package com.example.weatherproject.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.Weather
import com.example.weatherproject.databinding.ReportItemBinding
import com.example.weatherproject.utils.DateUtils.Companion.getDayOfWeek
import com.example.weatherproject.utils.GlideUtils.Companion.getIcon

class ReportAdapter(private val weatherList: List<Weather>) :
    RecyclerView.Adapter<ReportAdapter.ReportViewHolder>() {

    inner class ReportViewHolder(private val binding: ReportItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun holderBindingRun(holder: ReportViewHolder, weatherItem: Weather) {
            holder.binding.run {
                textDayName.text = getDayOfWeek(weatherItem.date)
                textDayDegrees.text = weatherItem.temperature.toString()
                getIcon(holder, weatherItem.image, imgWeather)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val binding = ReportItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReportViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        val weatherItem = weatherList[position]
        holder.holderBindingRun(holder, weatherItem)
    }

    override fun getItemCount() = weatherList.size
}