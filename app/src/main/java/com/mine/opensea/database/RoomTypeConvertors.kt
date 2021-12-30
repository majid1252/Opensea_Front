package com.mine.opensea.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mine.opensea.database.models.Collection
import com.mine.opensea.database.models.PrimaryAssetContract
import com.mine.opensea.database.models.Trait

class RoomTypeConvertors {
    @TypeConverter
    fun fromCollectionsList(countryLang: List<Collection>): String? {
        val type = object : TypeToken<List<Collection>>() {}.type
        return Gson().toJson(countryLang, type)
    }

    @TypeConverter
    fun toCollectionsList(countryLangString: String?): List<Collection> {
        val type = object : TypeToken<List<Collection>>() {}.type
        return Gson().fromJson(countryLangString, type)
    }

    @TypeConverter
    fun fromPrimaryAssetContractsList(countryLang: List<PrimaryAssetContract>): String? {
        val type = object : TypeToken<List<PrimaryAssetContract>>() {}.type
        return Gson().toJson(countryLang, type)
    }

    @TypeConverter
    fun toPrimaryAssetContractList(countryLangString: String?): List<PrimaryAssetContract> {
        val type = object : TypeToken<List<PrimaryAssetContract>>() {}.type
        return Gson().fromJson(countryLangString, type)
    }

    @TypeConverter
    fun fromStringList(countryLang: List<String?>?): String? {
        val type = object : TypeToken<List<String?>?>() {}.type
        return Gson().toJson(countryLang, type)
    }

    @TypeConverter
    fun toStringList(countryLangString: String?): List<String?>? {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(countryLangString, type)
    }

    @TypeConverter
    fun fromTraitsList(countryLang: List<Trait>?): String? {
        val type = object : TypeToken<List<Trait>>() {}.type
        return Gson().toJson(countryLang, type)
    }

    @TypeConverter
    fun toTraitsList(countryLangString: String?): List<Trait> {
        val type = object : TypeToken<List<Trait>>() {}.type
        return Gson().fromJson(countryLangString, type)
    }

}