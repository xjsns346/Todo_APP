package com.example.todoapp.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

object CalendarUtil {

    fun getCurrentDateInfo(): DateInfo {
        val now = LocalDate.now()
        return DateInfo(
            weekday = now.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()),
            solarDate = now.format(DateTimeFormatter.ofPattern("yyyy年M月d日"))
        )
    }

    fun getEndOfDayTimestamp(): Long {
        val now = LocalDate.now()
        val endOfDay = now.atTime(23, 59, 59)
        return endOfDay.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli()
    }

    data class DateInfo(
        val weekday: String,
        val solarDate: String
    )
}
