package com.mine.opensea.database.models

import com.google.gson.annotations.SerializedName

data class TradedAsset(

        @field:SerializedName("token_id")
        val tokenId: String? = null,

        @field:SerializedName("decimals")
        val decimals: Any? = null
)