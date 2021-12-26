package com.mine.opensea.database.models

import com.google.gson.annotations.SerializedName

data class User(

        @field:SerializedName("username")
        val username: String? = null
)