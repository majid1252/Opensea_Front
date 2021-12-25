package com.mine.opensea.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mine.opensea.database.models.CollectionModel

class RoomTypeConvertors {
    @TypeConverter
    fun fromCollectionsList(countryLang: List<CollectionModel>): String? {
        val type = object : TypeToken<List<CollectionModel>>() {}.type
        return Gson().toJson(countryLang, type)
    }

    @TypeConverter
    fun toCollectionsList(countryLangString: String?): List<CollectionModel> {
        val type = object : TypeToken<List<CollectionModel>>() {}.type
        return Gson().fromJson<List<CollectionModel>>(countryLangString, type)
    }

}