package com.example.weatherproject.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.Weather
import com.example.weatherproject.databinding.ReportItemBinding

class ReportAdapter(private val weatherList: List<Weather>) :
    RecyclerView.Adapter<ReportAdapter.ReportViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val binding = ReportItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReportViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        val weatherItem = weatherList[position]
        holder.binding.run {
            textDayName.text = DateConvert().getDayOfWeek(weatherItem.date)
            textDayDegrees.text = weatherItem.temperature.toString()
            GlideUtils().getIcon(holder, weatherItem.image, imgWeather)
        }
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    inner class ReportViewHolder(val binding: ReportItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}