package com.sir.rickandmorty.cache.room.entities.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

abstract class ListTypeConverter<T> {
    private val gson = Gson()

    @TypeConverter
    fun listFromString(value: String?): List<T> {
        return value?.let {
            val listType = object : TypeToken<List<T>>() {}.type
            gson.fromJson(value, listType)
        } ?: emptyList()
    }

    @TypeConverter
    fun listToString(list: List<T>?): String? {
        return list?.let {
            gson.toJson(list)
        }
    }
}