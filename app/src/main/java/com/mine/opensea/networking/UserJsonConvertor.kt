package com.mine.opensea.networking

import android.util.Log
import android.widget.EditText
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.mine.opensea.database.models.Collection
import com.mine.opensea.database.models.User
import com.mine.opensea.database.models.Username
import io.reactivex.rxjava3.core.Observable
import java.lang.reflect.Type

class UserJsonConvertor : JsonDeserializer<User> {
    override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
    ): User {
        return createUserFromJson(json)
    }

    fun createUserFromJson(json: JsonElement?): User {
        val userObject = User()
        if (json == null) {
            return userObject
        }

        val jsonObject = json.asJsonObject

        if (json.isJsonObject && json.asJsonObject.get("user")?.isJsonObject!!) {
            return userObject.apply {
                address = jsonObject.get("address").toString()
                profileImgUrl = jsonObject.get("profile_img_url").toString()
                user = Username(jsonObject.get("user").asJsonObject.get("username").toString())
                config = jsonObject.get("config").toString()
            }
        } else if (json.isJsonObject && json.asJsonObject.get("user")?.isJsonPrimitive!!) {
            return userObject.apply {
                address = jsonObject.get("address").toString()
                profileImgUrl = jsonObject.get("profile_img_url").toString()
                user = jsonObject.get("user")
                config = jsonObject.get("config").toString()
            }
        }
        return userObject
    }
}