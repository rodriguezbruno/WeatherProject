package com.example.weatherproject.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideUtils {
    companion object {
        fun getIcon(holder: ReportAdapter.ReportViewHolder, icon: String, imgView: ImageView) {
            Glide
                .with(holder.itemView.context)
                .load(icon)
                .into(imgView)
        }
    }
}