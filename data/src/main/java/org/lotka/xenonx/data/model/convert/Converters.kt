package org.lotka.xenonx.data.model.convert

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.lotka.xenonx.data.model.Tag
import org.lotka.xenonx.data.model.TeamMember

@ProvidedTypeConverter
class Converters(private val gson: Gson) {
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }
    @TypeConverter
    fun fromTagList(value: List<Tag>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Tag>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toTagList(value: String): List<Tag> {
        val gson = Gson()
        val type = object : TypeToken<List<Tag>>() {}.type
        return gson.fromJson(value, type)
    }

}
