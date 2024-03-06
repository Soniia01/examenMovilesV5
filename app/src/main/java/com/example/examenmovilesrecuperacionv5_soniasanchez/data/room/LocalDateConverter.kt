package com.example.examenmovilesrecuperacionv5_soniasanchez.data.room

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter


object LocalDateConverter {
    @TypeConverter
    fun fromString(value: String?): LocalDate? {
        return value?.let { LocalDate.parse(it) }
    }

    @TypeConverter
    fun toString(date: LocalDate?): String? {
        return date?.format(DateTimeFormatter.ISO_LOCAL_DATE)
    }
}