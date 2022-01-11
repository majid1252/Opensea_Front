package com.mine.opensea.database.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "collections_table")
data class Collection(

        @PrimaryKey(autoGenerate = true)
        var id: Long,

        @field:SerializedName("slug")
        val slug: String,

        @field:SerializedName("external_url")
        val externalUrl: String? = null,

        @field:SerializedName("short_description")
        val shortDescription: String? = null,

        @field:SerializedName("featured")
        val featured: Boolean? = null,

        /** there is problem with opensea api which returns different types...
         *...for single field [traits] so for now just got commented.

        @field:SerializedName("traits")
        val traits: List<Trait>? = null,

         */

        @field:SerializedName("hidden")
        val hidden: Boolean? = null,

        @field:SerializedName("wiki_url")
        val wikiUrl: String? = null,

        @field:SerializedName("twitter_username")
        val twitterUsername: String? = null,

        @field:SerializedName("primary_asset_contracts")
        val primaryAssetContracts: List<PrimaryAssetContract?>? = null,

        @field:SerializedName("default_to_fiat")
        val defaultToFiat: Boolean? = null,

        @field:SerializedName("description")
        val description: String? = null,

        @field:SerializedName("opensea_buyer_fee_basis_points")
        val openseaBuyerFeeBasisPoints: String? = null,

        @field:SerializedName("discord_url")
        val discordUrl: String? = null,

        @field:SerializedName("large_image_url")
        val largeImageUrl: String? = null,

        @field:SerializedName("payout_address")
        val payoutAddress: String? = null,

        @Embedded
        @field:SerializedName("stats")
        val stats: Stats? = null,

        @field:SerializedName("opensea_seller_fee_basis_points")
        val openseaSellerFeeBasisPoints: String? = null,

        @field:SerializedName("banner_image_url")
        val bannerImageUrl: String? = null,

        @field:SerializedName("safelist_request_status")
        val safelistRequestStatus: String? = null,

        @field:SerializedName("featured_image_url")
        val featuredImageUrl: String? = null,

        @field:SerializedName("instagram_username")
        val instagramUsername: String? = null,

        @field:SerializedName("is_subject_to_whitelist")
        val isSubjectToWhitelist: Boolean? = null,

        @field:SerializedName("image_url")
        val imageUrl: String? = null,

        @field:SerializedName("dev_seller_fee_basis_points")
        val devSellerFeeBasisPoints: String? = null,

        @field:SerializedName("medium_username")
        val mediumUsername: String? = null,

        @field:SerializedName("telegram_url")
        val telegramUrl: String? = null,

        @Embedded
        @field:SerializedName("display_data")
        val displayData: DisplayData? = null,

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("chat_url")
        val chatUrl: String? = null,

        @field:SerializedName("created_date")
        val createdDate: String? = null,

        @field:SerializedName("only_proxied_transfers")
        val onlyProxiedTransfers: Boolean? = null,

        @field:SerializedName("require_email")
        val requireEmail: Boolean? = null,

        @field:SerializedName("dev_buyer_fee_basis_points")
        val devBuyerFeeBasisPoints: String? = null
)