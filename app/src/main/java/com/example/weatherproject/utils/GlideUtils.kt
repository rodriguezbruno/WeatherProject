package com.example.weatherproject.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

private const val URL_ICON = "https://openweathermap.org/img/wn/"
private const val ICON_EXTENSION = ".png"

class GlideUtils {
    fun getIcon(holder: ReportAdapter.ReportViewHolder, icon: String, imgView: ImageView) {
        Glide
            .with(holder.itemView.context)
            .load(URL_ICON + icon + ICON_EXTENSION)
            .into(imgView)
        return
    }
}