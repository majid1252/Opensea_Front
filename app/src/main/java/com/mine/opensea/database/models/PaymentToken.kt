package com.mine.opensea.database.models

import com.google.gson.annotations.SerializedName

data class PaymentToken(

        @field:SerializedName("symbol")
        val symbol: String? = null,

        @field:SerializedName("address")
        val address: String? = null,

        @field:SerializedName("image_url")
        val imageUrl: String? = null,

        @field:SerializedName("decimals")
        val decimals: Int? = null,

        @field:SerializedName("name")
        val name: String? = null,

        @field:SerializedName("usd_price")
        val usdPrice: String? = null,

        @field:SerializedName("id")
        val id: Int? = null,

        @field:SerializedName("eth_price")
        val ethPrice: String? = null
)