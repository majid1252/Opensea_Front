package com.mine.opensea.database.models

import com.google.gson.annotations.SerializedName

data class LastSale(
        @field:SerializedName("asset_bundle")
        val assetBundle: Any? = null,

        @field:SerializedName("event_type")
        val eventType: String? = null,

        @field:SerializedName("quantity")
        val quantity: String? = null,

        @field:SerializedName("total_price")
        val totalPrice: String? = null,

        @field:SerializedName("auction_type")
        val auctionType: Any? = null,

        @field:SerializedName("payment_token")
        val paymentToken: PaymentToken? = null,

        @field:SerializedName("created_date")
        val createdDate: String? = null,

        @field:SerializedName("asset")
        val asset: TradedAsset? = null,

        @field:SerializedName("event_timestamp")
        val eventTimestamp: String? = null,

        @field:SerializedName("transaction")
        val transaction: Transaction? = null
)