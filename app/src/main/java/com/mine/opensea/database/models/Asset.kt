package com.mine.opensea.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "assets_table")
data class Asset(

        @PrimaryKey
        @field:SerializedName("token_id")
        val tokenId: String? = null,

        @field:SerializedName("top_bid")
        val topBid: Any? = null,

        @field:SerializedName("traits")
        val traits: List<Trait?>? = null,

        @field:SerializedName("image_thumbnail_url")
        val imageThumbnailUrl: String? = null,

        @field:SerializedName("image_preview_url")
        val imagePreviewUrl: String? = null,

        @field:SerializedName("description")
        val description: Any? = null,

        @field:SerializedName("supports_wyvern")
        val supportsWyvern: Boolean? = null,

        @field:SerializedName("asset_contract")
        val assetContract: PrimaryAssetContract? = null,

        @field:SerializedName("last_sale")
        val lastSale: LastSale? = null,

        @field:SerializedName("sell_orders")
        val sellOrders: Any? = null,

        @field:SerializedName("highest_buyer_commitment")
        val highestBuyerCommitment: Any? = null,

        @field:SerializedName("transfer_fee_payment_token")
        val transferFeePaymentToken: Any? = null,

        @field:SerializedName("id")
        val id: Int? = null,

        @field:SerializedName("related_assets")
        val relatedAssets: List<Any?>? = null,

        @field:SerializedName("token_metadata")
        val tokenMetadata: String? = null,

        @field:SerializedName("auctions")
        val auctions: List<Any?>? = null,

        @field:SerializedName("owner")
        val owner: User? = null,

        @field:SerializedName("is_presale")
        val isPresale: Boolean? = null,

        @field:SerializedName("animation_url")
        val animationUrl: Any? = null,

        @field:SerializedName("creator")
        val creator: User? = null,

        @field:SerializedName("num_sales")
        val numSales: Int? = null,

        @field:SerializedName("image_url")
        val imageUrl: String? = null,

        @field:SerializedName("collection")
        val collection: Collection? = null,

        @field:SerializedName("image_original_url")
        val imageOriginalUrl: String? = null,

        @field:SerializedName("external_link")
        val externalLink: String? = null,

        @field:SerializedName("transfer_fee")
        val transferFee: Any? = null,

        @field:SerializedName("background_color")
        val backgroundColor: Any? = null,

        @field:SerializedName("animation_original_url")
        val animationOriginalUrl: Any? = null,

        @field:SerializedName("ownership")
        val ownership: Any? = null,

        @field:SerializedName("decimals")
        val decimals: Any? = null,

        @field:SerializedName("name")
        val name: String? = null,

        @field:SerializedName("orders")
        val orders: List<Any?>? = null,

        @field:SerializedName("permalink")
        val permalink: String? = null,

        @field:SerializedName("top_ownerships")
        val topOwnerships: List<TopOwnershipsItem?>? = null,

        @field:SerializedName("listing_date")
        val listingDate: Any? = null
)