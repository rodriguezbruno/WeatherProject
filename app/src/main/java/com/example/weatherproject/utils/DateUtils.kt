package com.example.weatherproject.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateUtils {
    companion object {
        private const val DATE_FORMAT_ALL_DAY = "EEEE dd MMMM yyyy"
        private const val DATE_FORMAT_DAY_NAME = "EEEE"

        fun getDayOfWeek(longDate: Long): String {
            return SimpleDateFormat(
                DATE_FORMAT_DAY_NAME,
                Locale.getDefault()
            ).format(Date(longDate * 1000))
        }
    }
}