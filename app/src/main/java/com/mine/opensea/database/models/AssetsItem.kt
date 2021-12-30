package com.mine.opensea.database.models

import com.google.gson.annotations.SerializedName

data class AssetsItem(

        @field:SerializedName("address")
        val address: String? = null,

        @field:SerializedName("id")
        val id: String? = null,

        @field:SerializedName("owner")
        val owner: User? = null,

        @field:SerializedName("animation_url")
        val animationUrl: Any? = null,

        @field:SerializedName("num_sales")
        val numSales: Int? = null,

        @field:SerializedName("image_thumbnail_url")
        val imageThumbnailUrl: String? = null,

        @field:SerializedName("image_url")
        val imageUrl: String? = null,

        @field:SerializedName("image_preview_url")
        val imagePreviewUrl: String? = null,

        @field:SerializedName("description")
        val description: String? = null,

        @field:SerializedName("collection")
        val collection: Collection? = null,

        @field:SerializedName("image_original_url")
        val imageOriginalUrl: String? = null,

        @field:SerializedName("external_link")
        val externalLink: Any? = null,

        @field:SerializedName("asset_contract")
        val assetContract: AssetContract? = null,

        @field:SerializedName("token_id")
        val tokenId: String? = null,

        @field:SerializedName("background_color")
        val backgroundColor: Any? = null,

        @field:SerializedName("animation_original_url")
        val animationOriginalUrl: Any? = null,

        @field:SerializedName("decimals")
        val decimals: Int? = null,

        @field:SerializedName("name")
        val name: String? = null,

        @field:SerializedName("permalink")
        val permalink: String? = null,

        @field:SerializedName("token_metadata")
        val tokenMetadata: String? = null
)