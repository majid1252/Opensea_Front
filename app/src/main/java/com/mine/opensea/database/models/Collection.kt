package com.mine.opensea.database.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Entity(tableName = "collections_table")
@Parcelize
data class Collection(

        @PrimaryKey(autoGenerate = true)
        var id: Long,

        @field:SerializedName("slug")
        val slug: String,

        @field:SerializedName("external_url")
        val externalUrl: String? = "",

        @field:SerializedName("short_description")
        val shortDescription: String? = "",

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
        val wikiUrl: String? = "",

        @field:SerializedName("twitter_username")
        val twitterUsername: String? = "",

        @field:SerializedName("primary_asset_contracts")
        val primaryAssetContracts: List<PrimaryAssetContract?>? = null,

        @field:SerializedName("default_to_fiat")
        val defaultToFiat: Boolean? = null,

        @field:SerializedName("description")
        val description: String? = "",

        @field:SerializedName("opensea_buyer_fee_basis_points")
        val openseaBuyerFeeBasisPoints: String? = "",

        @field:SerializedName("discord_url")
        val discordUrl: String? = "",

        @field:SerializedName("large_image_url")
        val largeImageUrl: String? = "",

        @field:SerializedName("payout_address")
        val payoutAddress: String? = "",

        @Embedded
        @field:SerializedName("stats")
        val stats: Stats? = null,

        @field:SerializedName("opensea_seller_fee_basis_points")
        val openseaSellerFeeBasisPoints: String? = "",

        @field:SerializedName("banner_image_url")
        val bannerImageUrl: String? = "",

        @field:SerializedName("safelist_request_status")
        val safelistRequestStatus: String? = "",

        @field:SerializedName("featured_image_url")
        val featuredImageUrl: String? = "",

        @field:SerializedName("instagram_username")
        val instagramUsername: String? = "",

        @field:SerializedName("is_subject_to_whitelist")
        val isSubjectToWhitelist: Boolean? = null,

        @field:SerializedName("image_url")
        val imageUrl: String? = "",

        @field:SerializedName("dev_seller_fee_basis_points")
        val devSellerFeeBasisPoints: String? = "",

        @field:SerializedName("medium_username")
        val mediumUsername: String? = "",

        @field:SerializedName("telegram_url")
        val telegramUrl: String? = "",

        @Embedded
        @field:SerializedName("display_data")
        val displayData: DisplayData? = null,

        @field:SerializedName("name")
        val name: String? = "",

        @field:SerializedName("chat_url")
        val chatUrl: String? = "",

        @field:SerializedName("created_date")
        val createdDate: String? = "",

        @field:SerializedName("only_proxied_transfers")
        val onlyProxiedTransfers: Boolean? = null,

        @field:SerializedName("require_email")
        val requireEmail: Boolean? = null,

        @field:SerializedName("dev_buyer_fee_basis_points")
        val devBuyerFeeBasisPoints: String? = ""
) : Parcelable {
    private companion object : Parceler<Collection> {
        override fun Collection.write(parcel: Parcel, flags: Int) {
            parcel.writeString(this.bannerImageUrl)
            parcel.writeString(this.name)
            parcel.writeString(this.description)
            parcel.writeString(this.imageUrl)
            parcel.writeString(this.slug)
            parcel.writeLong(this.id)
        }

        override fun create(parcel: Parcel): Collection {
            return Collection(
                bannerImageUrl = parcel.readString()!!,
                name = parcel.readString()!!,
                description = parcel.readString()!!,
                imageUrl = parcel.readString()!!,
                slug = parcel.readString()!!,
                id = parcel.readLong()
            )
        }
    }

}