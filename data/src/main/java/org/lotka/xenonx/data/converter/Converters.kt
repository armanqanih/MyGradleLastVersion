package org.lotka.xenonx.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.lotka.xenonx.domain.model.Source

class NewsTypeConvertor {
    @TypeConverter
    fun fromSource(source: Source): String {
        val gson = Gson()
        return gson.toJson(source)
    }

    @TypeConverter
    fun toSource(sourceString: String): Source {
        val gson = Gson()
        val type = object : TypeToken<Source>() {}.type
        return gson.fromJson(sourceString, type)
    }
}