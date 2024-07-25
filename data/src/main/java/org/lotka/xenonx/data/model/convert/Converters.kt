package org.lotka.xenonx.data.model.convert

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import org.lotka.xenonx.data.local.entity.model.TeamMemberEntity
import org.lotka.xenonx.data.model.Tag

@ProvidedTypeConverter
class Converters(private val gson: Gson) {

    @TypeConverter
    fun fromStringListToString(value: List<String>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun fromStringToStringList(value: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromTagListToString(value: List<Tag>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun fromStringToTagList(value: String): List<Tag> {
        val type = object : TypeToken<List<Tag>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromTeamMemberListToString(value: List<TeamMemberEntity>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun fromStringToTeamMemberList(value: String): List<TeamMemberEntity> {
        val type = object : TypeToken<List<TeamMemberEntity>>() {}.type
        return gson.fromJson(value, type)
    }
}
