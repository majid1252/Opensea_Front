package com.mine.opensea.database.models

import androidx.room.Embedded
import androidx.room.Entity
import com.google.common.math.Stats
import com.google.gson.annotations.SerializedName

@Entity
data class CollectionModel(

    @field:SerializedName("short_description")
    val shortDescription: Any? = null,

    @field:SerializedName("featured")
    val featured: Boolean? = null,

    @Embedded
    @field:SerializedName("traits")
    val traits: TraitsModel? = null,

    @field:SerializedName("hidden")
    val hidden: Boolean? = null,

    @field:SerializedName("wiki_url")
    val wikiUrl: Any? = null,

    @field:SerializedName("twitter_username")
    val twitterUsername: Any? = null,

    @field:SerializedName("primary_asset_contracts")
    val primaryAssetContracts: List<Any?>? = null,

    @field:SerializedName("default_to_fiat")
    val defaultToFiat: Boolean? = null,

    @field:SerializedName("description")
    val description: Any? = null,

    @field:SerializedName("opensea_buyer_fee_basis_points")
    val openseaBuyerFeeBasisPoints: String? = null,

    @field:SerializedName("discord_url")
    val discordUrl: Any? = null,

    @field:SerializedName("external_url")
    val externalUrl: Any? = null,

    @field:SerializedName("large_image_url")
    val largeImageUrl: Any? = null,

    @field:SerializedName("payout_address")
    val payoutAddress: Any? = null,

    @Embedded
    @field:SerializedName("stats")
    val stats: Stats? = null,

    @field:SerializedName("opensea_seller_fee_basis_points")
    val openseaSellerFeeBasisPoints: String? = null,

    @field:SerializedName("banner_image_url")
    val bannerImageUrl: Any? = null,

    @field:SerializedName("safelist_request_status")
    val safelistRequestStatus: String? = null,

    @field:SerializedName("featured_image_url")
    val featuredImageUrl: Any? = null,

    @field:SerializedName("instagram_username")
    val instagramUsername: Any? = null,

    @field:SerializedName("slug")
    val slug: String? = null,

    @field:SerializedName("is_subject_to_whitelist")
    val isSubjectToWhitelist: Boolean? = null,

    @field:SerializedName("image_url")
    val imageUrl: Any? = null,

    @field:SerializedName("dev_seller_fee_basis_points")
    val devSellerFeeBasisPoints: String? = null,

    @field:SerializedName("medium_username")
    val mediumUsername: Any? = null,

    @field:SerializedName("telegram_url")
    val telegramUrl: Any? = null,

    @Embedded
    @field:SerializedName("display_data")
    val displayData: DisplayDataModel? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("chat_url")
    val chatUrl: Any? = null,

    @field:SerializedName("created_date")
    val createdDate: String? = null,

    @field:SerializedName("only_proxied_transfers")
    val onlyProxiedTransfers: Boolean? = null,

    @field:SerializedName("require_email")
    val requireEmail: Boolean? = null,

    @field:SerializedName("dev_buyer_fee_basis_points")
    val devBuyerFeeBasisPoints: String? = null
)