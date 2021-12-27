package com.mine.opensea.networking

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.mine.opensea.database.models.Collection
import java.lang.reflect.Type

class CollectionJsonConvertor : JsonDeserializer<Collection> {
    override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
    ): Collection {
        return Gson().fromJson(json?.asJsonObject?.get("collection"), Collection::class.java)
    }
}