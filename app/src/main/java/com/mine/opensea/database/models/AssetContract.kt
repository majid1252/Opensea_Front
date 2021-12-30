package com.mine.opensea.database.models

import com.google.gson.annotations.SerializedName

data class AssetContract(

        @field:SerializedName("owner")
        val owner: Int? = null,

        @field:SerializedName("symbol")
        val symbol: String? = null,

        @field:SerializedName("seller_fee_basis_points")
        val sellerFeeBasisPoints: Int? = null,

        @field:SerializedName("address")
        val address: String? = null,

        @field:SerializedName("opensea_version")
        val openseaVersion: Any? = null,

        @field:SerializedName("total_supply")
        val totalSupply: String? = null,

        @field:SerializedName("image_url")
        val imageUrl: String? = null,

        @field:SerializedName("description")
        val description: String? = null,

        @field:SerializedName("default_to_fiat")
        val defaultToFiat: Boolean? = null,

        @field:SerializedName("opensea_buyer_fee_basis_points")
        val openseaBuyerFeeBasisPoints: Int? = null,

        @field:SerializedName("collection")
        val collection: Collection? = null,

        @field:SerializedName("schema_name")
        val schemaName: String? = null,

        @field:SerializedName("external_link")
        val externalLink: String? = null,

        @field:SerializedName("dev_seller_fee_basis_points")
        val devSellerFeeBasisPoints: Int? = null,

        @field:SerializedName("buyer_fee_basis_points")
        val buyerFeeBasisPoints: Int? = null,

        @field:SerializedName("payout_address")
        val payoutAddress: String? = null,

        @field:SerializedName("opensea_seller_fee_basis_points")
        val openseaSellerFeeBasisPoints: Int? = null,

        @field:SerializedName("name")
        val name: String? = null,

        @field:SerializedName("asset_contract_type")
        val assetContractType: String? = null,

        @field:SerializedName("created_date")
        val createdDate: String? = null,

        @field:SerializedName("nft_version")
        val nftVersion: String? = null,

        @field:SerializedName("only_proxied_transfers")
        val onlyProxiedTransfers: Boolean? = null,

        @field:SerializedName("dev_buyer_fee_basis_points")
        val devBuyerFeeBasisPoints: Int? = null
)