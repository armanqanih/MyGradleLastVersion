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
    fun fromString(value: String?): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String>?): String? {
        return Gson().toJson(list)
    }


    @TypeConverter
    fun fromTagList(value: List<Tag>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toTagList(value: String): List<Tag>? {
        val listType = object : TypeToken<List<Tag>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromTeamMemberList(value: List<TeamMember>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toTeamMemberList(value: String): List<TeamMember>? {
        val listType = object : TypeToken<List<TeamMember>>() {}.type
        return gson.fromJson(value, listType)
    }




}
