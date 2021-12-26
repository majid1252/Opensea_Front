package com.mine.opensea.database.models

import com.google.gson.annotations.SerializedName

data class TopOwnershipsItem(

        @field:SerializedName("owner")
        val owner: Owner? = null,

        @field:SerializedName("quantity")
        val quantity: String? = null
)