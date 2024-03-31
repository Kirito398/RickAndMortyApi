package com.sir.rickandmorty.cache.room.entities.converters

import androidx.room.TypeConverter
import java.text.DateFormat
import java.util.Date

class DateTimeTypeConverter {

    @TypeConverter
    fun fromTimeStamp(value: String?): Date? {
        return value?.let { DateFormat.getDateTimeInstance().parse(it) }
    }

    @TypeConverter
    fun dateToTimeStamp(date: Date?): String? {
        return date?.time?.let { DateFormat.getDateTimeInstance().format(it) }
    }
}