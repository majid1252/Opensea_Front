package com.mine.opensea.database.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "bundles_table")
data class Bundle(

        @field:SerializedName("sell_orders")
        val sellOrders: List<SellOrdersItem?>? = null,

        @field:SerializedName("assets")
        val assets: List<Asset?>? = null,

        @field:SerializedName("name")
        val name: String? = null,

        @field:SerializedName("maker")
        val maker: User? = null,

        @field:SerializedName("description")
        val description: String? = null,

        @field:SerializedName("permalink")
        val permalink: String? = null,

        @field:SerializedName("slug")
        val slug: String? = null,

        @field:SerializedName("external_link")
        val externalLink: Any? = null,

        @field:SerializedName("asset_contract")
        val assetContract: PrimaryAssetContract? = null,

        @field:SerializedName("schemas")
        val schemas: List<String?>? = null
)