package com.mine.opensea.database.models

import com.google.gson.annotations.SerializedName

data class FromAccount(

        @field:SerializedName("address")
        val address: String? = null,

        @field:SerializedName("profile_img_url")
        val profileImgUrl: String? = null,

        @field:SerializedName("user")
        val user: User? = null,

        @field:SerializedName("config")
        val config: String? = null
)