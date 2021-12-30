package com.mine.opensea.database.models

import com.google.gson.annotations.SerializedName

data class User(

		@field:SerializedName("address")
		var address: String? = null,

		@field:SerializedName("profile_img_url")
		var profileImgUrl: String? = null,

		@field:SerializedName("user")
		var user: Any? = null,

		@field:SerializedName("config")
		var config: String? = null
)
